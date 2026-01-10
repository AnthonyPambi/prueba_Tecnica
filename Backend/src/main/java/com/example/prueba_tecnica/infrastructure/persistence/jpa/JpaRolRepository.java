package com.example.prueba_tecnica.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.infrastructure.persistence.entities.RolEntity;

public interface JpaRolRepository extends JpaRepository<RolEntity, Integer> {

    /**
     * Busca un rol por su nombre (ej: 'ADMIN', 'USER').
     * Útil para asignar roles por defecto durante el registro de usuarios.
     */
    Optional<RolEntity> findByRolName(String rolName);
}
