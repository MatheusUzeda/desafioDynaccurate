package com.dynaccurateDesafio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynaccurate.form.UsuarioForm;
import com.dynaccurateDesafio.dto.UsuarioDto;
import com.dynaccurateDesafio.model.Usuario;
import com.dynaccurateDesafio.repository.UsuarioRepository;

@Service
public class UsuarioCrudService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioDto obterUsuarioPeloId(String id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()) {
			return new UsuarioDto(usuario.get());
		}
		return null;
	}

	public List<UsuarioDto> obterListaUsuario() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public UsuarioDto cadastrarUsuario(UsuarioForm form) {
		Usuario usuario = repository.save(new Usuario(form));
		return new UsuarioDto(usuario);

	}

	public UsuarioDto atualizarUsuario(String id, UsuarioForm form) {
		form.setId(id);
		Usuario usuario = repository.save(new Usuario(form));
		return new UsuarioDto(usuario);
	}

	public void excluirUsuario(String id) {
		repository.deleteById(id);
	}

}
