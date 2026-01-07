package com.example.prueba_tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.entity.RolOpciones; // IMPORTANTE: Cambiado Persona por RolOpciones

public interface RolOpcionesRepository extends JpaRepository<RolOpciones, Integer> {
    // Aquí no necesitas métodos extra por ahora para cumplir con el diagrama
}