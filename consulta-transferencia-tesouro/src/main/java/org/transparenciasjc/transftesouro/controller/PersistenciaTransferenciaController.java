package org.transparenciasjc.transftesouro.controller;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.transparenciasjc.transftesouro.model.Fundo;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.TransferenciaTesouro;
import org.transparenciasjc.transftesouro.model.dto.DadosTransferencia;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;
import org.transparenciasjc.transftesouro.service.impl.FundoService;
import org.transparenciasjc.transftesouro.service.impl.MunicipioService;
import org.transparenciasjc.transftesouro.service.impl.TransferenciaTesouroService;

/**
 * 
 * Salva dados do DTO no banco de dados
 * 
 * @author wsiqueir
 *
 */
@Stateless
public class PersistenciaTransferenciaController {
	
	@Inject
	TransferenciaTesouroService transferenciaService;
	
	@Inject MunicipioService municipioService;
	
	@Inject FundoService fundoService;
	
	Logger logger = Logger.getLogger(PersistenciaTransferenciaController.class);

	@Asynchronous
	public void salva(TransferenciaDTO transferenciaDTO) {
		logger.info("Salvando dados: " + transferenciaDTO);
		String nomeMun = transferenciaDTO.getMunicipio();
		String sigla = transferenciaDTO.getEstado();
		Municipio municipio = municipioService.porNomeESigla(sigla, nomeMun);
		for (DadosTransferencia dados : transferenciaDTO.getDadosTransferencia()) {
			Fundo fundo = fundoService.buscaFundoPorNome(dados.getTipo());
			TransferenciaTesouro transferencia = new TransferenciaTesouro();
			transferencia.setAno(dados.getAno());
			transferencia.setMes(dados.getMes());
			transferencia.setFundo(fundo);
			transferencia.setMunicipio(municipio);
			transferencia.setValor(dados.getValor());
			salvaDados(transferencia);
		}
		logger.info("Dados salvos para: " + transferenciaDTO);
	}

	/**
	 * 
	 * Salva transferência em uma nova transação
	 * @param transferencia
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void salvaDados(TransferenciaTesouro transferencia) {
		try {
			transferenciaService.salvar(transferencia);
		} catch (ConstraintViolationException e) {
			logger.debug("Dados para a seguinte não salvos. Dados repetidos? " + transferencia, e);
		}
	}
	
}
