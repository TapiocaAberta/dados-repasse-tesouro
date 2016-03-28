package org.transparenciasjc.transftesouro.service.impl;

import java.util.function.Supplier;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.transparenciasjc.transftesouro.model.Fundo;
import org.transparenciasjc.transftesouro.service.Service;

public class FundoService extends Service<Fundo> {
	
	public Fundo buscaFundoPorNome(String nome) {
		TypedQuery<Fundo> buscaPorNome = em.createNamedQuery("Fundo.porNome", Fundo.class);
		buscaPorNome.setParameter("nome", nome);
		return buscaPorNome.getSingleResult();		
	}
	
	public Fundo buscaFundoPorNomeOuCria(String nome, Supplier<Fundo> novoFundoSupplier) {
		try {
			return buscaFundoPorNome(nome);
		} catch (NoResultException e) {
			Fundo novo = novoFundoSupplier.get();
			this.salvar(novo);
			return novo;
		}		
	}

}
