package org.transparenciasjc.transftesouro.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DadosTransferencia {
	
	private String tipo;
	private int ano;
	private int mes;
	private float valor;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}