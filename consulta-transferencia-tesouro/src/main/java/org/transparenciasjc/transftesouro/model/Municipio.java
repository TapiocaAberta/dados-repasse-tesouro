package org.transparenciasjc.transftesouro.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "municipio")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "cache-classes-basicas")
public class Municipio {

	@Id
	@Column(name = "mun_id")
	private long id;

	@Column(name = "mun_nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "estado_est_id")
	private Estado estado;

	public Municipio() {
		super();
	}

	public Municipio(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Municipio(long id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
	

	@Override
	public String toString() {
		return nome + " - " + estado.getSigla();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}