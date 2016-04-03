package org.transparenciasjc.transftesouro.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.transparenciasjc.transftesouro.leitor.LeitorDadosTesouro;
import org.transparenciasjc.transftesouro.leitor.TesouroScrapper;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.TransferenciaTesouro;
import org.transparenciasjc.transftesouro.model.dto.DadosTransferencia;
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
	
	public TransferenciaDTO buscaDadosParaMunicipio(Municipio municipio) {
		LocalDateTime atual = LocalDateTime.now();
		LocalDateTime anterior = atual.minusMonths(1);
		int ano = atual.getYear();
		int mes = atual.getMonthValue();
		int anoAnterior = anterior.getYear();
		int mesAnterior = anterior.getMonthValue();
		TransferenciaDTO retorno = null;
		if(transferenciaService.haValores(municipio, anoAnterior, mesAnterior)) {
			List<TransferenciaTesouro> busca = transferenciaService.busca(municipio);
			retorno = TransferenciaDTO.transforma(busca);			
		} else {
			retorno = leitorScrapper.leDadosMunicipio(municipio);
			// remove dados do mês atual, pois podem estar desatualizados
			List<DadosTransferencia> dados = retorno.getDadosTransferencia()
					.stream().filter(d -> !(d.getAno() == ano && d.getMes() == mes))
					.collect(Collectors.toList());
			retorno.setDadosTransferencia(dados);
			persistenciaTransferenciaController.salva(retorno);
		}
		return retorno;
	}

}
