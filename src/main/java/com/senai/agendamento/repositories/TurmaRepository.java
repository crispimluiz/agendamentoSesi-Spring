package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.agendamento.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

}
