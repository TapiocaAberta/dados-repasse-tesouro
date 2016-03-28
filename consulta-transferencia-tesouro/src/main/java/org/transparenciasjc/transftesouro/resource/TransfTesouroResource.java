package org.transparenciasjc.transftesouro.resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.transparenciasjc.transftesouro.controller.TransferenciaController;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.DadosTransferencia;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;
import org.transparenciasjc.transftesouro.service.impl.MunicipioService;


@Stateless
@Path("/")
@Produces("application/json;charset=utf-8")
public class TransfTesouroResource {

	@Inject
	TransferenciaController controller;
	
	@Inject
	MunicipioService municipioService;
	
	@GET
	@Path("municipio/{munId}/transferencias")
	public TransferenciaDTO transferenciasPorMunicipio(@PathParam("munId") long munId) {
		return controller.buscaDadosParaMunicipio(munId);
	}
	
	@GET
	@Path("municipio/{munId}/transferencias/ano/{ano}")
	public TransferenciaDTO transferenciasPorMunicipioAno(@PathParam("munId") long munId, @PathParam("ano") int ano) {
		TransferenciaDTO transferencia = controller.buscaDadosParaMunicipio(munId);
		List<DadosTransferencia> dados = transferencia.getDadosTransferencia().stream().filter(d -> d.getAno() == ano).collect(Collectors.toList());
		transferencia.setDadosTransferencia(dados);
		return transferencia;
	}
	
	@GET
	@Path("estado/{sigla}/municipios")
	public Map<String, Long> municipiosPorEstado(@PathParam("sigla") String sigla) {
		return 	municipioService.porSiglaEstado(sigla).stream().collect(Collectors.toMap(Municipio::getNome, Municipio::getId));
	}
	
}
