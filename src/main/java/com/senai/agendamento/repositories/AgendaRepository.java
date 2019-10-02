package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.agendamento.domain.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}