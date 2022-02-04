package com.dynaccurateDesafio.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dynaccurateDesafio.dto.EventoDto;

@Document
public class Evento {

	@Id
	private String id;

	private String eventType;

	private LocalDateTime eventDateTime;
	
	private String idUsuario;
	
	public Evento() {	
	}

	public Evento(EventoDto dto) {
		this.eventType = dto.getEventType();
		this.eventDateTime = dto.getEventDateTime();
		this.idUsuario = dto.getIdUsuario();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	

	public void setEventDateTime(LocalDateTime eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
