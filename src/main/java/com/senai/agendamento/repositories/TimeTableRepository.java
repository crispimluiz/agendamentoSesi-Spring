package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.agendamento.domain.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

}