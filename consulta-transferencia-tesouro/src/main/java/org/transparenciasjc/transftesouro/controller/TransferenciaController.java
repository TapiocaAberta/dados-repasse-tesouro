package org.transparenciasjc.transftesouro.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.transparenciasjc.transftesouro.leitor.LeitorDadosTesouro;
import org.transparenciasjc.transftesouro.leitor.TesouroScrapper;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.TransferenciaTesouro;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;
import org.transparenciasjc.transftesouro.service.impl.MunicipioService;
import org.transparenciasjc.transftesouro.service.impl.TransferenciaTesouroService;

@Stateless
public class TransferenciaController {

	@Inject
	@TesouroScrapper
	LeitorDadosTesouro leitorScrapper;
	
	@Inject
	TransferenciaTesouroService transferenciaService;
	
	@Inject PersistenciaTransferenciaController persistenciaTransferenciaController;

	@Inject
	MunicipioService municipioService;
	
	Logger logger = Logger.getLogger(TransferenciaController.class);
	
	public TransferenciaDTO buscaDadosParaMunicipio(long munId) {
		Municipio municipio = municipioService.buscarPorId(munId);
		LocalDateTime ultimoMes = LocalDateTime.now().minusMonths(1);
		int ano = ultimoMes.getYear();
		int mes = ultimoMes.getMonthValue();
		TransferenciaDTO retorno = null;
		if(transferenciaService.haValores(municipio, ano, mes)) {
			List<TransferenciaTesouro> busca = transferenciaService.busca(municipio);
			retorno = TransferenciaDTO.transforma(busca);			
		} else {
			retorno = leitorScrapper.leDadosMunicipio(municipio);
			persistenciaTransferenciaController.salva(retorno);
		}
		return retorno;
	}

}
