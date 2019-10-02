package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.agendamento.domain.AgendaIntervalo;

public interface AgendaIntervaloRepository extends JpaRepository<AgendaIntervalo, Long> {

}
