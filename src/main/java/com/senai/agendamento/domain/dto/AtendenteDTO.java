package com.senai.agendamento.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.senai.agendamento.domain.Atendente;

public class AtendenteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preechimento Obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 Caracteres")
	private String nomeAtendente;

	public AtendenteDTO() {
	}

	public AtendenteDTO(Atendente obj) {
		id = obj.getId();
		nomeAtendente = obj.getNomeAtendente();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

}
