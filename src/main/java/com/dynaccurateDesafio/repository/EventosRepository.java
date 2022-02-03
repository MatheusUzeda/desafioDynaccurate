package com.dynaccurateDesafio.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dynaccurateDesafio.model.Evento;

public interface EventosRepository extends MongoRepository<Evento, String> {

	Page<Evento> findByIdUsuario(String id, Pageable paginacao);

}