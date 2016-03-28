package org.transparenciasjc.transftesouro.service.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.service.Service;

public class MunicipioService extends Service<Municipio> {
	
	public List<Municipio> porSiglaEstado(String sigla) {
		TypedQuery<Municipio> buscaMunicipios = em.createNamedQuery("Municipio.porSigla", Municipio.class);
		buscaMunicipios.setParameter("sigla", sigla);
		return buscaMunicipios.getResultList();	
	}

}
