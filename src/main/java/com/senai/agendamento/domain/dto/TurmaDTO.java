package com.senai.agendamento.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.senai.agendamento.domain.Turma;

public class TurmaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preechimento Obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 Caracteres")
	private String turmaDescricao;

	private char ativa;
	private String observacao;

	public TurmaDTO() {
	}

	public TurmaDTO(Turma obj) {
		turmaDescricao = obj.getTurmaDescricao();
		ativa = obj.getAtiva();
		observacao = obj.getObservacao();
	}

	public String getTurmaDescricao() {
		return turmaDescricao;
	}

	public void setTurmaDescricao(String turmaDescricao) {
		this.turmaDescricao = turmaDescricao;
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

}
