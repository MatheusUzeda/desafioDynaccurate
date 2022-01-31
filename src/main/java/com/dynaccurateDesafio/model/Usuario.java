package com.dynaccurateDesafio.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Usuario {

	@Id
	private String id;

	private String nickname;

	private LocalDateTime registrationDate = LocalDateTime.now();
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

}
