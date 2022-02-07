package com.dynaccurateDesafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynaccurate.form.EventoForm;
import com.dynaccurateDesafio.dto.EventoDto;
import com.dynaccurateDesafio.service.EventoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/usuario/evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@ApiOperation("Obter os eventos de um Usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Eventos do usuário não foram encontrados"),
			@ApiResponse(code = 200, message = "Eventos do usuário encontrados")})
	@GetMapping()
	public ResponseEntity<?> listaEventoUsuario(@RequestParam(required = true) String id,
			@RequestParam(required = false) String data,
			@PageableDefault(sort = "eventDateTime", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.eventoService.obterEventosUsuario(id, data, paginacao).isEmpty()
				? ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Eventos do usuário não foram encontrados! " + "Verifique o identificador de usuário, e "
								+ "se estiver buscando eventos por uma data específica o formato é dd/MM/yyyy.")
				: ResponseEntity.ok(this.eventoService.obterEventosUsuario(id, data, paginacao));
	}
	
	@ApiOperation("Cadastrar o evento de um Usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "O identificador de usuário, precisa ser válido"),
			@ApiResponse(code = 201, message = "Evento cadastrado")})
	@PostMapping()
	public ResponseEntity<?> cadastrarEvento(@RequestBody EventoForm evento) {
		return this.eventoService.cadastrarEvento(evento) == null
				? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O identificador de usuário, precisa ser válido !")
				: ResponseEntity.status(HttpStatus.CREATED).body(new EventoDto(evento));

	}

}
