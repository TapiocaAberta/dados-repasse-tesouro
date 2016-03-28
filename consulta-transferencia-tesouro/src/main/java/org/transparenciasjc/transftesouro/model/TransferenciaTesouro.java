package org.transparenciasjc.transftesouro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "transferencia_tesouro")
@NamedQueries(
		{@NamedQuery(name="TransferenciaTesouro.contaPorAnoMesMunicipio", query="SELECT COUNT(t) FROM TransferenciaTesouro t WHERE t.municipio = :municipio AND t.ano = :ano AND t.mes = :mes")}
		)
public class TransferenciaTesouro {

	@Id
	@GeneratedValue
	@Column(name = "trt_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "transferencia_tesouro_mun_id")
	private Municipio municipio;

	@ManyToOne
	@JoinColumn(name = "transferencia_tesouro_fnd_id")
	private Fundo fundo;

	@Column(name = "trt_ano")
	private int ano;

	@Column(name = "trt_mes")
	private int mes;

	@Column(name = "trt_valor")
	private float valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Fundo getFundo() {
		return fundo;
	}

	public void setFundo(Fundo fundo) {
		this.fundo = fundo;
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