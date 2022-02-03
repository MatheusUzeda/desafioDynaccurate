package com.dynaccurateDesafio.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.dynaccurate.form.UsuarioForm;

@Document
public class Usuario {

	@Id
	private String id;

	private String nickName;

	private LocalDateTime registrationDate;

	public Usuario() {
	}

	public Usuario(UsuarioForm form) {
		this.nickName = form.getNickName();
		this.registrationDate = form.getRegistrationDate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

}
