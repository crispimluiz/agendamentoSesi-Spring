package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.agendamento.domain.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}