package com.senai.agendamento.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Filial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "filial_id")
	private Integer filialId;

	@Column(name = "filial_descricao")
	private String filialDescricao;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public Filial() {
	}

	public Filial(Integer filialId, String filialDescricao, Empresa empresa) {
		super();
		this.filialId = filialId;
		this.filialDescricao = filialDescricao;
		this.empresa = empresa;
	}

	public Integer getFilialId() {
		return filialId;
	}

	public void setFilialId(Integer filialId) {
		this.filialId = filialId;
	}

	public String getFilialDescricao() {
		return filialDescricao;
	}

	public void setFilialDescricao(String filialDescricao) {
		this.filialDescricao = filialDescricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filialId == null) ? 0 : filialId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filial other = (Filial) obj;
		if (filialId == null) {
			if (other.filialId != null)
				return false;
		} else if (!filialId.equals(other.filialId))
			return false;
		return true;
	}

}
