package com.senai.agendamento.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));

	int horaInicioManha = calendar.get(Calendar.HOUR_OF_DAY);
	int horaTerminoManha = calendar.get(Calendar.HOUR_OF_DAY);
	int horaInicioTarde = calendar.get(Calendar.HOUR_OF_DAY);
	int horaTerminoTarde = calendar.get(Calendar.HOUR_OF_DAY);
	int minuto = calendar.get(Calendar.MINUTE);
	int periodoAtendimento = calendar.get(Calendar.MINUTE);
	int segundo = calendar.get(Calendar.SECOND);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agendamento_id")
	private Integer agendamentoId;

	@Column(name = "data_agendamento")
	@JsonFormat(pattern = "dd/MM/YYYY")
	private Date dataAgendamento;

	@Column(name = "ativo_agendamento")
	private boolean ativoAgendamento = false;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PESSOA_ID")
	private Set<Pessoa> pessoa;

	public Long periodoAtendimento() {
		horaInicioManha = 8;
		horaTerminoManha = 11;
		horaInicioTarde = 13;
		horaTerminoTarde = 15;

		while (periodoAtendimento >= horaInicioManha && periodoAtendimento <= horaTerminoManha
				&& periodoAtendimento >= horaInicioTarde && periodoAtendimento <= horaTerminoTarde) {
			periodoAtendimento = (minuto * 20);
		}
		return (long) periodoAtendimento;
	}

	public Agendamento() {
	}

	public Agendamento(Calendar calendar, int horaInicioManha, int horaTerminoManha, int horaInicioTarde,
			int horaTerminoTarde, int minuto, int periodoAtendimento, int segundo, Integer agendamentoId,
			Date dataAgendamento, boolean ativoAgendamento, Set<Pessoa> pessoa) {
		super();
		this.calendar = calendar;
		this.horaInicioManha = horaInicioManha;
		this.horaTerminoManha = horaTerminoManha;
		this.horaInicioTarde = horaInicioTarde;
		this.horaTerminoTarde = horaTerminoTarde;
		this.minuto = minuto;
		this.periodoAtendimento = periodoAtendimento;
		this.segundo = segundo;
		this.agendamentoId = agendamentoId;
		this.dataAgendamento = dataAgendamento;
		this.ativoAgendamento = ativoAgendamento;
		this.pessoa = pessoa;
	}

	public Integer getAgendamentoId() {
		return agendamentoId;
	}

	public void setAgendamentoId(Integer agendamentoId) {
		this.agendamentoId = agendamentoId;
	}

	public boolean isAtivoAgendamento() {
		return ativoAgendamento;
	}

	public void setAtivoAgendamento(boolean ativoAgendamento) {
		this.ativoAgendamento = ativoAgendamento;
	}

	public Set<Pessoa> getPessoa() {
		return pessoa;
	}

	public void setPessoa(Set<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public int getHoraInicioManha() {
		return horaInicioManha;
	}

	public void setHoraInicioManha(int horaInicioManha) {
		this.horaInicioManha = horaInicioManha;
	}

	public int getHoraTerminoManha() {
		return horaTerminoManha;
	}

	public void setHoraTerminoManha(int horaTerminoManha) {
		this.horaTerminoManha = horaTerminoManha;
	}

	public int getHoraInicioTarde() {
		return horaInicioTarde;
	}

	public void setHoraInicioTarde(int horaInicioTarde) {
		this.horaInicioTarde = horaInicioTarde;
	}

	public int getHoraTerminoTarde() {
		return horaTerminoTarde;
	}

	public void setHoraTerminoTarde(int horaTerminoTarde) {
		this.horaTerminoTarde = horaTerminoTarde;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getPeriodoAtendimento() {
		return periodoAtendimento;
	}

	public void setPeriodoAtendimento(int periodoAtendimento) {
		this.periodoAtendimento = periodoAtendimento;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agendamentoId == null) ? 0 : agendamentoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (agendamentoId == null) {
			if (other.agendamentoId != null)
				return false;
		} else if (!agendamentoId.equals(other.agendamentoId))
			return false;
		return true;
	}

}
