package com.example.prueba_tecnica.domain.ports;

import com.example.prueba_tecnica.domain.models.Persona;

import java.util.List;
import java.util.Optional;

import com.example.prueba_tecnica.domain.models.Persona;

public interface IPersonaRepository {
    
    // Guarda o actualiza una persona en el sistema
    Persona save(Persona persona);

    // Busca una persona por su ID interno
    Optional<Persona> findById(Integer id);

    // Crucial para validar que no se repitan cédulas
    Optional<Persona> findByIdentificacion(String identificacion);

    // Devuelve todas las personas (útil para reportes o administración)
    List<Persona> findAll();

    // Verifica la existencia sin traer todo el objeto (más eficiente)
    boolean existsByIdentificacion(String identificacion);
}