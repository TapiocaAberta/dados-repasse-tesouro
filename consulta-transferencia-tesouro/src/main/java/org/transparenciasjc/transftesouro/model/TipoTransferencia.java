package org.transparenciasjc.transftesouro.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "tipo_transferencia", uniqueConstraints = @UniqueConstraint(columnNames = { "ttr_nome" }))
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "cache-classes-basicas")
public class TipoTransferencia {

	@Id
	@GeneratedValue
	@Column(name = "ttr_id")
	private long id;

	@Column(name = "ttr_nome")
	private String nome;

	@Column(name = "ttr_desc", length = 10000)
	private String descricao;

	public TipoTransferencia(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public TipoTransferencia() {
		super();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}