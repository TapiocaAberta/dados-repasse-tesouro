package org.transparenciasjc.transftesouro.service.impl;

import java.util.function.Supplier;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.transparenciasjc.transftesouro.model.Estado;
import org.transparenciasjc.transftesouro.service.Service;

public class EstadoService extends Service<Estado> {

	public Estado buscaEstadoPorSigla(String sigla) {
		TypedQuery<Estado> buscaPorSigla = em.createNamedQuery("Estado.porSigla", Estado.class);
		buscaPorSigla.setParameter("sigla", sigla);
		return buscaPorSigla.getSingleResult();		
	}
	
	public Estado buscaEstadoPorSiglaOuCria(String sigla, Supplier<Estado> novoEstadoSupplier) {
		try {
			return buscaEstadoPorSigla(sigla);
		} catch (NoResultException e) {
			Estado novo = novoEstadoSupplier.get();
			this.salvar(novo);
			return novo;
		}		
	}
}
