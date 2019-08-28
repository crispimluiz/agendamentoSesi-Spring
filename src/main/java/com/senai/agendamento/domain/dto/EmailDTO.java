package com.senai.agendamento.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Email(message="CPF não Cadastrado!")
	private String email;
	
	public EmailDTO(){
	}

	public String getEmail() {
		return email;
	}

	public void setCpf(String email) {
		this.email = email;
	}
}