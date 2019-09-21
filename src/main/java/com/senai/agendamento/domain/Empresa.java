package com.senai.agendamento.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empresa_id")
	private Integer empresaId;

	@Column(name = "descricao_empresa")
	private String empresaDescricao;
	
	
	@JsonIgnore//Para não ficar indo e voltado informação sem parar
	@OneToMany(mappedBy="empresa")
	private List<Filial> filials = new ArrayList<>();
	

	public Empresa() {
	}

	public Empresa(Integer empresaId, String empresaDescricao) {
		super();
		this.empresaId = empresaId;
		this.empresaDescricao = empresaDescricao;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public String getEmpresaDescricao() {
		return empresaDescricao;
	}

	public void setEmpresaDescricao(String empresaDescricao) {
		this.empresaDescricao = empresaDescricao;
	}
	
	public List<Filial> getFilials() {
		return filials;
	}

	public void setFilials(List<Filial> filials) {
		this.filials = filials;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresaId == null) ? 0 : empresaId.hashCode());
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
		Empresa other = (Empresa) obj;
		if (empresaId == null) {
			if (other.empresaId != null)
				return false;
		} else if (!empresaId.equals(other.empresaId))
			return false;
		return true;
	}

}
