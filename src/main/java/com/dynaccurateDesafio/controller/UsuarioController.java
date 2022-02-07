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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioCrudService crudService;

	@ApiOperation("Obter um usuário especifico pelo seu id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuário encontrado"),
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> obterUsuario(@PathVariable String id) {
		UsuarioDto usuario = this.crudService.obterUsuarioPeloId(id);
		return usuario != null ? ResponseEntity.ok(usuario)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
	}

	@ApiOperation("Obter uma lista com todos usuários cadastrados ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "lista de usuários cadastrados") })
	@GetMapping
	public ResponseEntity<?> listarUsuario() {
		return ResponseEntity.ok(this.crudService.obterListaUsuario());
	}
	
	@ApiOperation("Realizar o cadastro de um usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Usuário cadastrado com sucesso") })
	@PostMapping
	public ResponseEntity<?> realizarCadastroUsuario(@RequestBody UsuarioForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.crudService.cadastrarUsuario(form));
	}
	
	@ApiOperation("Atualizar os dados de um usuário pelo seu id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuário atualizado com sucesso") })
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> atualizacaoUsuario(@PathVariable String id, @RequestBody UsuarioForm form) {
		return ResponseEntity.ok(this.crudService.atualizarUsuario(id, form));
	}

	@ApiOperation("Remover um usuário pelo seu id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuário excluido") })
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> removerUsuario(@PathVariable String id) {
		this.crudService.excluirUsuario(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
