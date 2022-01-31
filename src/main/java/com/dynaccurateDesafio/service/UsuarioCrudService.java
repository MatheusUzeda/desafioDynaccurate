package com.dynaccurateDesafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynaccurateDesafio.model.Usuario;
import com.dynaccurateDesafio.repository.UsuarioRepository;

@Service
public class UsuarioCrudService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Usuario> obterUsuarioPeloId(String id) {
		return repository.findById(id);
	}

	public List<Usuario> obterListaUsuario() {
		return repository.findAll();
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario atualizarUsuario(String id, Usuario usuario) {
		usuario.setId(id);
		return repository.save(usuario);
	}

	public void excluirUsuario(String id) {
		repository.deleteById(id);
	}

}
