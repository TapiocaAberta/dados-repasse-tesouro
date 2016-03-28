package org.transparenciasjc.transftesouro.resource;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.transparenciasjc.transftesouro.controller.TransferenciaController;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;


@Stateless
@Path("transferencia")
public class TransfTesouroResource {

	@Inject
	TransferenciaController controller;
	
	@GET
	@Path("/{munId}")
	@Produces("application/json")
	public TransferenciaDTO transferenciasPorMunicipio(@PathParam("munId") long munId) {
		return controller.buscaDadosParaMunicipio(munId);
	}
	
}
