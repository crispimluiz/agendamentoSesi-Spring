package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.agendamento.domain.TimeBox;

@Repository
public interface TimeBoxRepository extends JpaRepository<TimeBox, Long>{

}
