package org.transparenciasjc.transftesouro.controller;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.transparenciasjc.transftesouro.leitor.LeitorDadosTesouro;
import org.transparenciasjc.transftesouro.leitor.TesouroScrapper;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;
import org.transparenciasjc.transftesouro.service.impl.MunicipioService;

@Stateless
public class TransferenciaController {

	@Inject
	@TesouroScrapper
	LeitorDadosTesouro leitorScrapper;

	@Inject
	MunicipioService municipioService;
	
	public TransferenciaDTO buscaDadosParaMunicipio(long munId) {
		Municipio municipio = municipioService.buscarPorId(munId);
		// TODO: Adicionar persistência, ainda sem persistência dos dados
		// Lógica: Quando não tiver dados do município para o mês anterior,
		// buscar os dados usando o scrapper, caso contrário, trazer do banco
		// Quando os dados virem do scrapper, disparar um evento CDI para
		// persistência e logging
		return leitorScrapper.leDadosMunicipio(municipio);
	}

}
