package com.senai.agendamento.domain.enums;


public enum PessoaEnum {

	PESSOAFISICA(1, "Pessoa Física");

	private int cod;
	private String descricao;

	private PessoaEnum (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static PessoaEnum toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (PessoaEnum x : PessoaEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id Inválido" + cod);

	}

}
