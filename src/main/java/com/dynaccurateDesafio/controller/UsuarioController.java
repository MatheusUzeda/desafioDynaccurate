package com.dynaccurateDesafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynaccurate.form.UsuarioForm;
import com.dynaccurateDesafio.dto.UsuarioDto;
import com.dynaccurateDesafio.service.UsuarioCrudService;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioCrudService crudService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> obterUsuario(@PathVariable String id) {
		UsuarioDto usuario = this.crudService.obterUsuarioPeloId(id);
		return usuario != null ? ResponseEntity.ok(usuario)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}

	@GetMapping
	public ResponseEntity<?> listarUsuario() {
		return ResponseEntity.ok(this.crudService.obterListaUsuario());
	}

	@PostMapping
	public ResponseEntity<?> realizarCadastroUsuario(@RequestBody UsuarioForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.crudService.cadastrarUsuario(form));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> atualizacaoUsuario(@PathVariable String id, @RequestBody UsuarioForm form) {
		return ResponseEntity.ok(this.crudService.atualizarUsuario(id, form));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> removerUsuario(@PathVariable String id) {
		this.crudService.excluirUsuario(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
