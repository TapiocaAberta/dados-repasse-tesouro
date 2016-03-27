package org.transparenciasjc.transftesouro.controller;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.transparenciasjc.transftesouro.leitor.LeitorDadosTesouro;
import org.transparenciasjc.transftesouro.leitor.TesouroScrapper;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;

@Stateless
public class TransferenciaController {

	@Inject
	@TesouroScrapper
	LeitorDadosTesouro leitorScrapper;

	public TransferenciaDTO buscaDadosParaMunicipio(Municipio municipio) {
		// TODO: Adicionar persistência, ainda sem persistência dos dados
		// Lógica: Quando não tiver dados do município para o mês anterior,
		// buscar os dados usando o scrapper, caso contrário, trazer do banco
		return leitorScrapper.leDadosMunicipio(municipio);
	}

}
