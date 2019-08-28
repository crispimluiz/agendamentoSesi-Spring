package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.agendamento.domain.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer>{

}
