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
import com.dynaccurate.form.EventoForm;
import com.dynaccurateDesafio.dto.EventoDto;
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

	public EventoDto cadastrarEvento(EventoForm evento) {
		EventoDto eventoDto = new EventoDto(evento);
		if(this.crudService.obterUsuarioPeloId(eventoDto.getIdUsuario()) != null) {
			this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_EVENTO, evento);
			return eventoDto;
		}
		eventoDto = null;
		return eventoDto;

	}

	public Page<EventoDto> obterEventosUsuario(String id, String data, Pageable paginacao) {
		if (data == null) {
			 Page<Evento> eventos = this.eventosRepository.findByIdUsuario(id, paginacao);
			 return eventos.map(EventoDto::new);
		}
		Page<Evento> eventosUsuario = this.eventosRepository.findByIdUsuario(id, paginacao);
		List<Evento> collect = eventosUsuario.stream()
				.filter(t -> DateTimeFormatter.ofPattern("dd/MM/yyyy").format(t.getEventDateTime()).equals(data))
				.collect(Collectors.toList());
		Page<Evento> eventos = new PageImpl<Evento>(collect, paginacao, collect.size());
		return eventos.map(EventoDto::new);
	
	}

}
