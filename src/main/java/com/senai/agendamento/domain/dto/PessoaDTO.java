package com.senai.agendamento.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.senai.agendamento.domain.Pessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preechimento Obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 Caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Email(message = "CPF Inválido")
	private String cpf;

	private Date dataNascimento;

	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Email(message = "Email Inválido")
	private String email;

	private char ativa;
	private String observacao;

	private char pessoaResponsavel;

	private String senha;

	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		dataNascimento = obj.getDataNascimento();
		email = obj.getEmail();
		ativa = obj.getAtiva();
		observacao = obj.getObservacao();
		pessoaResponsavel = obj.getPessoaResponsavel();
		senha = obj.getSenha();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getAtiva() {
		return ativa;
	}

	public void setAtiva(char ativa) {
		this.ativa = ativa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public char getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(char pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
