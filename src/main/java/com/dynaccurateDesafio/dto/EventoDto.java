package com.dynaccurateDesafio.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.dynaccurate.form.EventoForm;
import com.dynaccurateDesafio.model.Evento;

public class EventoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eventType;

	private LocalDateTime eventDateTime = LocalDateTime.now();

	private String idUsuario;

	public EventoDto(Evento evento) {

		this.eventType = evento.getEventType();
		this.eventDateTime = evento.getEventDateTime();
		this.idUsuario = evento.getIdUsuario();
	}

	public EventoDto(EventoForm evento) {

		this.eventType = evento.getEventType();
		this.idUsuario = evento.getIdUsuario();
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getEventDateTime() {
		return eventDateTime;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
