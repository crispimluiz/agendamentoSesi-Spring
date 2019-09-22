package com.senai.agendamento.domain.dto;

import java.io.Serializable;
import java.time.Instant;

import com.senai.agendamento.domain.TimeBox;

public class TimeBoxDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant start;
	private Instant end;

	public TimeBoxDTO() {
	}

	public TimeBoxDTO(Instant start, Instant end) {
		this.start = start;
		this.end = end;
	}
	
	public TimeBoxDTO(TimeBox entity) {
		this.start = entity.getStart();
		this.end = entity.getEnd();
	}

	public Instant getStart() {
		return start;
	}

	public void setStart(Instant start) {
		this.start = start;
	}

	public Instant getEnd() {
		return end;
	}

	public void setEnd(Instant end) {
		this.end = end;
	}
}
