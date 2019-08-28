package com.senai.agendamento.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senai.agendamento.domain.enums.Perfil;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PESSOA_ID")
	private Integer id;
	private String nome;
	private String cpf;

	@Column(name = "DATA_NASCIMENTO")
	@JsonFormat(pattern = "dd/MM/YYYY")
	private Date dataNascimento;

	@Column(unique = true)
	private String email;

	private char ativa;
	private String observacao;

	@Column(name = "PESSOA_RESPONSAVEL")
	private char pessoaResponsavel;

	// Para não ver o bcrypt da senha
	@JsonIgnore
	private String senha;

	@ElementCollection // Cria uma tabela telefone
	@CollectionTable(name = "celular")
	private Set<String> celular = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "turma_id")
	private Set<Turma> turma;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "atendente_id")
	private Set<Atendente> atendente;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Pessoa() {
		addPerfil(Perfil.PESSOA);
	}

	public Pessoa(Integer id, String nome, String cpf, Date dataNascimento, String email, char ativa, String observacao,
			char pessoaResponsavel, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.ativa = ativa;
		this.observacao = observacao;
		this.pessoaResponsavel = pessoaResponsavel;
		this.senha = senha;
		addPerfil(Perfil.PESSOA);
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<String> getCelular() {
		return celular;
	}

	public void setCelular(Set<String> celular) {
		this.celular = celular;
	}

	public char getPessoaResponsavel() {
		return pessoaResponsavel;
	}

	public void setPessoaResponsavel(char pessoaResponsavel) {
		this.pessoaResponsavel = pessoaResponsavel;
	}

	public Set<Turma> getTurma() {
		return turma;
	}

	public void setTurma(Set<Turma> turma) {
		this.turma = turma;
	}

	public Set<Atendente> getAtendente() {
		return atendente;
	}

	public void setAtendente(Set<Atendente> atendente) {
		this.atendente = atendente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [nome=");
		builder.append(nome);
		builder.append(", cpf=");
		builder.append(cpf);
		builder.append("]");
		return builder.toString();
	}

}
