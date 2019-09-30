package com.senai.agendamento.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.senai.agendamento.domain.TimeBox;
import com.senai.agendamento.domain.TimeTable;
import com.senai.agendamento.domain.TimeTableEntry;
import com.senai.agendamento.domain.dto.TimeBoxDTO;
import com.senai.agendamento.domain.dto.TimesDTO;
import com.senai.agendamento.domain.enums.Perfil;
import com.senai.agendamento.repositories.TimeTableRepository;
import com.senai.agendamento.security.UserSS;
import com.senai.agendamento.services.Exception.AuthorizationException;
import com.senai.agendamento.services.Exception.DataIntegrityException;
import com.senai.agendamento.services.Exception.ObjectNotFoundException;

@Service
public class TimeTableService {
	@Autowired
	private TimeTableRepository repository;
	
	public TimesDTO times(Long timeTableId, LocalDate localDate) {
	
		TimeTable timeTable = repository.getOne(timeTableId);
		
		Stream<TimeTableEntry> entries = timeTable.getEntries().stream()
				.filter(e -> e.getDay().equals(localDate.getDayOfWeek())).sorted();
				
		TimesDTO times = new TimesDTO(localDate);
		times.addAllTimeBoxes(entries.map(e -> new TimeBoxDTO(toTimeBox(localDate, e))).collect(Collectors.toList()));
		return times;
	}

	public List<TimesDTO> times(Long timeTableId, LocalDate startDate, LocalDate endDate) {

		List<TimesDTO> list = new ArrayList<>();
		
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1L)) {
			list.add(times(timeTableId, date));
		}
		
		return list;
	}
	
	private TimeBox toTimeBox(LocalDate localDate, TimeTableEntry entry) {
		Instant start = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getStartMillisecond());
		Instant end = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).plusMillis(entry.getEndMillisecond());
		return new TimeBox(null, start, end);
	}
	
	@Transactional
	public TimeTable insert(TimeTable obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}

	public TimeTable update(TimeTable obj) {
		TimeTable newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	
	public List<TimeTable> findAll() {
		return repository.findAll();
	}
	
	public Page<TimeTable> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public TimeTable fromDTO(@Valid TimesDTO objDto) {	
		return new TimeTable (null, null);
	}


	private void updateData(TimeTable newObj, TimeTable obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setId(obj.getId());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public TimeTable find(Long id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<TimeTable> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + TimeTable.class.getName()));
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir");
		}
	}
}

	
	/*
	@Transactional(readOnly = true)
	public List<TimeBox> generateTimeBoxes(IntervalDTO dto) {
		
		LocalDateTime dt1 = LocalDateTime.now(ZoneId.systemDefault());
		
//		Instant i1 = d1.toEpochSecond(time, offset)
		
		TimeTable timeTable = repository.getOne(dto.getTimeTableId());

		return timeTable.getEntries().stream()
				.filter(e -> e.getStartSecond() >= dto.getStart().getEpochSecond() && e.getEndSecond() <= dto.getEnd().getEpochSecond())
				.map(e -> e.toTimeBox())
				.sorted()
				.collect(Collectors.toList());	
	}*/	

