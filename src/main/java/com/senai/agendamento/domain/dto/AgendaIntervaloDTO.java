package com.senai.agendamento.domain.dto;

import java.io.Serializable;
import java.time.DayOfWeek;

import com.senai.agendamento.domain.Agenda;
import com.senai.agendamento.domain.AgendaIntervalo;

public class AgendaIntervaloDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	Long id;
	DayOfWeek day;
	Long startHour;
	Long startMinute;
	Long endHour;
	Long endMinute;
	Long agendaId;
	
	public AgendaIntervaloDTO() {
	}
	
	public AgendaIntervaloDTO(AgendaIntervalo entity) {
		id = entity.getId();
		day = entity.getDay();
		startMinute = entity.getStartMinute();
		startHour = entity.getStartHour();
		endHour = entity.getEndHour();
		endMinute = entity.getEndMinute();
		if (entity.getAgenda() != null) {
			agendaId = entity.getAgenda().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Long getStartHour() {
		return startHour;
	}

	public void setStartHour(Long startHour) { 
		this.startHour = startHour;
	}

	public Long getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Long startMinute) {
		this.startMinute = startMinute;
	}

	public Long getEndHour() {
		return endHour;
	}

	public void setEndHour(Long endHour) {
		this.endHour = endHour;
	}

	public Long getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Long endMinute) {
		this.endMinute = endMinute;
	}

	public Long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Long agendaId) {
		this.agendaId = agendaId;
	}
	
	public AgendaIntervalo toEntity() {
		Agenda agenda = new Agenda(agendaId, null, null, null);
		return new AgendaIntervalo(id, day, startHour, startMinute, endHour, endMinute, agenda);
	}
	
}
