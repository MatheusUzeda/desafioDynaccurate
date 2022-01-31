package com.dynaccurateDesafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dynaccurateDesafio.model.Usuario;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}