package com.example.prueba_tecnica.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.infrastructure.persistence.entities.UsuarioEntity;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    /**
     * Busca por nombre de usuario o correo electrónico.
     * Esto permite el login flexible requerido.
     */
    Optional<UsuarioEntity> findByUserNameOrMail(String username, String mail);

    /**
     * Busca un usuario por su correo electrónico.
     * Útil para validar que el correo sea único en el registro.
     */
    Optional<UsuarioEntity> findByMail(String mail);

    /**
     * Busca por nombre de usuario exacto.
     * Útil para procesos de autenticación y auditoría.
     */
    Optional<UsuarioEntity> findByUserName(String username);

    // Agrega este método para que UsuarioRepositoryImpl deje de marcar error
    boolean existsByUserName(String userName);
}