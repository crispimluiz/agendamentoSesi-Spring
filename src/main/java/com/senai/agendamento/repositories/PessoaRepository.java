package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.senai.agendamento.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	//Não necessita de ser envolvida no banco de dados
		//Deixa mais rápida e diminiu o lokin
		@Transactional(readOnly=true)
		Pessoa findByCpf(String cpf);
		//findByEmail - Busca e-mail.
}
