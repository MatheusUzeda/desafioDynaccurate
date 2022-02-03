package com.dynaccurateDesafio.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.dynaccurateDesafio.model.Usuario;

public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String nickName;

	private LocalDateTime registrationDate;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nickName = usuario.getNickname();
		this.registrationDate = usuario.getRegistrationDate();
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

	public LocalDateTime setRegistrationDate() {
		return registrationDate;
	}

}
