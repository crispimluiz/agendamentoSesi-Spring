package com.senai.agendamento.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.senai.agendamento.domain.Agenda;
import com.senai.agendamento.domain.AgendaIntervalo;

@Repository
public interface AgendaIntervaloRepository extends JpaRepository<AgendaIntervalo, Long> {
	
	@Transactional(readOnly=true)
	Page<Agenda> findByAgenda(Agenda agenda, Pageable pageRequest);
}
