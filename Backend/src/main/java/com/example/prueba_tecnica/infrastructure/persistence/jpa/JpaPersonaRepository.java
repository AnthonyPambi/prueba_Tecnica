package com.example.prueba_tecnica.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.infrastructure.persistence.entities.PersonaEntity;

public interface JpaPersonaRepository extends JpaRepository<PersonaEntity, Integer> {
    Optional<PersonaEntity> findByIdentificacion(String identificacion);
}