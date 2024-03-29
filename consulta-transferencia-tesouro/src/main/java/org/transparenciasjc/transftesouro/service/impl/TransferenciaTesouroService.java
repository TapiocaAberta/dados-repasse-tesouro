package org.transparenciasjc.transftesouro.service.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.transparenciasjc.transftesouro.model.Fundo;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.TransferenciaTesouro;
import org.transparenciasjc.transftesouro.service.Service;

public class TransferenciaTesouroService extends Service<TransferenciaTesouro> {

	public boolean haValores(Municipio municipio, int ano, int mes) {
		TypedQuery<Long> buscaValores = em.createNamedQuery(
				"TransferenciaTesouro.contaPorAnoMesMunicipio", Long.class);
		buscaValores.setParameter("municipio", municipio);
		buscaValores.setParameter("ano", ano);
		buscaValores.setParameter("mes", mes);
		long contagem = buscaValores.getSingleResult();
		return contagem > 0;
	}
	public boolean haValores(Municipio municipio, Fundo fundo, int ano, int mes) {
		TypedQuery<Long> buscaValores = em.createNamedQuery(
				"TransferenciaTesouro.contaPorAnoMesMunicipioFundo", Long.class);
		buscaValores.setParameter("municipio", municipio);
		buscaValores.setParameter("fundo", fundo);
		buscaValores.setParameter("ano", ano);
		buscaValores.setParameter("mes", mes);
		long contagem = buscaValores.getSingleResult();
		return contagem > 0;
	}

	public List<TransferenciaTesouro> busca(Municipio municipio) {
		TypedQuery<TransferenciaTesouro> buscaTransferencias = em
				.createNamedQuery("TransferenciaTesouro.buscaPorMunicipio",
						TransferenciaTesouro.class);
		buscaTransferencias.setParameter("municipio", municipio);
		return buscaTransferencias.getResultList();
	}

}
