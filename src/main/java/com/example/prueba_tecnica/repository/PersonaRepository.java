package com.example.prueba_tecnica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    // Método necesario para validar duplicados
    Optional<Persona> findByIdentificacion(String identificacion);
}