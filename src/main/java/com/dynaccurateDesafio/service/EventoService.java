package com.dynaccurateDesafio.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dynaccurate.constantes.RabbitMQConstantes;
import com.dynaccurate.dto.EventoDto;
import com.dynaccurateDesafio.model.Evento;
import com.dynaccurateDesafio.repository.EventosRepository;

@Service
public class EventoService {
	@Autowired
	private EventosRepository eventosRepository;

	@Autowired
	private RabbitMQService rabbitMQService;
	
	@Autowired
	private UsuarioCrudService crudService;

	public EventoDto cadastrarEvento(EventoDto evento) {
		if(this.crudService.obterUsuarioPeloId(evento.getIdUsuario()).isPresent()) {
			this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_EVENTO, evento);
			return evento;
		}
		evento = null;
		return evento;

	}

	public Page<Evento> obterEventosUsuario(String id, String data, Pageable paginacao) {
		if (data == null) {
			return this.eventosRepository.findByIdUsuario(id, paginacao);
		}
		Page<Evento> eventosUsuario = this.eventosRepository.findByIdUsuario(id, paginacao);
		List<Evento> collect = eventosUsuario.stream()
				.filter(t -> DateTimeFormatter.ofPattern("dd/MM/yyyy").format(t.getEventDateTime()).equals(data))
				.collect(Collectors.toList());
		Page<Evento> eventos = new PageImpl<Evento>(collect, paginacao, collect.size());
		return eventos;
	}

}
