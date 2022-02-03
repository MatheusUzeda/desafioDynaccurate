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
import com.dynaccurateDesafio.service.EventoService;

@RestController
@RequestMapping(path = "/usuario/evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;

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

	@PostMapping()
	public ResponseEntity<?> cadastrarEvento(@RequestBody EventoForm evento) {
		return this.eventoService.cadastrarEvento(evento) == null
				? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O identificador de usuário, precisa ser válido !")
				: ResponseEntity.status(HttpStatus.CREATED).body(evento);

	}

}
