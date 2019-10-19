package com.senai.agendamento.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.senai.agendamento.domain.Agenda;

public class AgendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;

	public AgendaDTO() {
	}

	public AgendaDTO(Agenda obj) {
		id = obj.getId();
		description = obj.getDescription();
		startDate = obj.getStartDate();
		endDate = obj.getEndDate();
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

}
