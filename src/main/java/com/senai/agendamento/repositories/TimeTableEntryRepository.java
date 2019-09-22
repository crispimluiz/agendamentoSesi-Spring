package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.agendamento.domain.TimeTableEntry;

public interface TimeTableEntryRepository extends JpaRepository<TimeTableEntry, Long> {

}
