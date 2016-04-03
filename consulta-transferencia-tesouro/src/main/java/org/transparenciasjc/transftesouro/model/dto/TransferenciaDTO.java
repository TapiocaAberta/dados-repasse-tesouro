package org.transparenciasjc.transftesouro.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.transparenciasjc.transftesouro.model.TransferenciaTesouro;

/**
 * 
 * Objeto que carrega as informações básicas de transferências do tesouro
 * 
 * @author wsiqueir
 *
 */
@XmlRootElement
public class TransferenciaDTO {

	private String estado;
	private String municipio;
	private List<DadosTransferencia> dadosTransferencia;

	public static TransferenciaDTO transforma(List<TransferenciaTesouro> lista) {
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setEstado(lista.get(0).getMunicipio().getEstado().getSigla());
		transferenciaDTO.setMunicipio(lista.get(0).getMunicipio().getNome());
		transferenciaDTO.setDadosTransferencia(new ArrayList<>());
		for (TransferenciaTesouro t : lista) {
			DadosTransferencia dados = new DadosTransferencia();
			dados.setAno(t.getAno());
			dados.setMes(t.getMes());
			dados.setTipo(t.getFundo().getNome());
			dados.setValor(t.getValor());
			transferenciaDTO.getDadosTransferencia().add(dados);
		}
		return transferenciaDTO;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public List<DadosTransferencia> getDadosTransferencia() {
		return dadosTransferencia;
	}

	public void setDadosTransferencia(
			List<DadosTransferencia> dadosTransferencia) {
		this.dadosTransferencia = dadosTransferencia;
	}	
	
	@Override
	public String toString() {
		return "[municipio: "  + municipio +", total de transferências: " + dadosTransferencia.size() + "]";
	}

}
