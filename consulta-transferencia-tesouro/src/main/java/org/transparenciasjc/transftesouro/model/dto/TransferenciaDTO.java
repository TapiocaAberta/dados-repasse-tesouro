package org.transparenciasjc.transftesouro.model.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferenciaDTO {

	private String estado;
	private String municipio;
	private List<DadosTransferencia> dadosTransferencia;

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

}
