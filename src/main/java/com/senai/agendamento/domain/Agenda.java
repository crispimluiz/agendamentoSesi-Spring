package com.senai.agendamento.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_agenda")
public class Agenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "agenda")
	private List<AgendaIntervalo> entries = new ArrayList<>();
	
	public Agenda() {
	}

	public Agenda(Long id, String description, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<AgendaIntervalo> getEntries() {
		return entries;
	}

	public void addEntry(AgendaIntervalo entry) {
		validadeNoConflicts(entry);
		entries.add(entry);
	}

	private void validadeNoConflicts(AgendaIntervalo entry) {
		for (AgendaIntervalo e : entries) {
			if (e.conflicts(entry)) {
				throw new IllegalArgumentException("The entry conflicts with timetable");
			}
		}
	}
}
