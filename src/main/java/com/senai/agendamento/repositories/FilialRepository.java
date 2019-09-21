package com.senai.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.agendamento.domain.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer>{

}
