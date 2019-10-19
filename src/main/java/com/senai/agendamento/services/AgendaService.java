package com.senai.agendamento.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.Agenda;
import com.senai.agendamento.domain.AgendaHorario;
import com.senai.agendamento.domain.AgendaIntervalo;
import com.senai.agendamento.domain.dto.AgendaDTO;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.AgendaHorarioRepository;
import com.senai.agendamento.repositories.AgendaRepository;
import com.senai.agendamento.security.UserSS;
import com.senai.agendamento.services.Exception.AuthorizationException;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class AgendaService {
	
	@Autowired
	private AgendaRepository repository;
	
	@Autowired
	private AgendaHorarioRepository agendaHorarioRepository;
	
	public List<AgendaHorario> gerarHorarios(Long agendaId, LocalDate localDate) {
	
		Agenda agenda = repository.getOne(agendaId);
		
		Stream<AgendaIntervalo> entries = agenda.getEntries().stream()
				.filter(e -> e.getDay().equals(localDate.getDayOfWeek())).sorted();

		List<AgendaHorario> list = new ArrayList<>();

		entries.forEach(e -> list.add(toTimeBox(localDate, e, agenda)));
		return list;
	}

	public void gerarHorarios(Long agendaId) {
		
		Agenda agenda = repository.getOne(agendaId);
		LocalDate startDate = agenda.getStartDate();
		LocalDate endDate = agenda.getEndDate();

		List<AgendaHorario> list = new ArrayList<>();
		
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1L)) {
			list.addAll(gerarHorarios(agendaId, date));
		}

		agendaHorarioRepository.saveAll(list);
	}
	
	private AgendaHorario toTimeBox(LocalDate localDate, AgendaIntervalo entry, Agenda agenda) {
		Instant start = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().plusMillis(entry.getStartMillisecond());
		Instant end = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().plusMillis(entry.getEndMillisecond());
		return new AgendaHorario(null, start, end, agenda);
	}
	
	@Transactional
	public Agenda insert(Agenda obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}
	
	public Agenda fromDTO(AgendaDTO objDto) {
		return new Agenda (null, objDto.getDescription(), objDto.getStartDate(), objDto.getEndDate());
	}

	public Agenda update(Agenda obj) {
		Agenda newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	
	public List<Agenda> findAllAgenda() {
		return repository.findAll();
	}
	
	public Page<Agenda> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	private void updateData(Agenda newObj, Agenda obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setId(obj.getId());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Agenda find(Long id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Agenda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Agenda.class.getName()));
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}

	public AgendaHorario getHorario(Long horarioId) {
		return agendaHorarioRepository.findById(horarioId).get();
	}
}

	
	/*
	@Transactional(readOnly = true)
	public List<AgendaHorario> generateTimeBoxes(IntervalDTO dto) {
		
		LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());
		
//		Instant i1 = d1.toEpochSecond(time, offset)
		
		Agenda timeTable = repository.getOne(dto.getTimeTableId());

		return timeTable.getEntries().stream()
				.filter(e -> e.getStartSecond() >= dto.getStart().getEpochSecond() && e.getEndSecond() <= dto.getEnd().getEpochSecond())
				.map(e -> e.toTimeBox())
				.sorted()
				.collect(Collectors.toList());	
	}*/	

