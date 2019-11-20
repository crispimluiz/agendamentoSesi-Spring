package com.senai.agendamento.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senai.agendamento.domain.Agenda;
import com.senai.agendamento.domain.AgendaHorario;
import com.senai.agendamento.domain.dto.AgendaDTO;
import com.senai.agendamento.domain.dto.AgendaIntervaloDTO;
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
//Gerar o templade antes de gerar os dias propriamente ditos.
	@PostMapping(value = "/{agendaId}/inseririntervalos")
	public ResponseEntity<List<AgendaIntervaloDTO>> inserirIntervalos(@PathVariable Long agendaId, @RequestBody List<AgendaIntervaloDTO> list) {
		list = service.inserirIntervalos(agendaId, list);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/horario/{horarioId}")
	public ResponseEntity<AgendaHorario> horario(@PathVariable Long horarioId) {
		AgendaHorario entity = service.getHorario(horarioId);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping(value = "/{horarioId}/horario")
	public ResponseEntity<List<AgendaDTO>> findAll(@PathVariable Long Id) {
		List<Agenda> list  = service.findAll();
		List<AgendaDTO> listDto = list.stream().map(obj -> new AgendaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Agenda>> findAll() {
		List<Agenda> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AgendaDTO objDto) {
		Agenda obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
