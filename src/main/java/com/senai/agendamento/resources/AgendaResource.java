package com.senai.agendamento.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.agendamento.domain.AgendaHorario;
import com.senai.agendamento.services.AgendaService;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaResource {
	
	@Autowired
	private AgendaService service;	
	
	@PostMapping(value = "/gerarhorarios/{agendaId}")
	public ResponseEntity<Void> gerarHorarios(@PathVariable Long agendaId) {
		service.gerarHorarios(agendaId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/horario/{horarioId}")
	public ResponseEntity<AgendaHorario> horario(@PathVariable Long horarioId) {
		AgendaHorario entity = service.getHorario(horarioId);
		return ResponseEntity.ok().body(entity);
	}
	
}
