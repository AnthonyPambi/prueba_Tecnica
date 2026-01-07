package com.example.prueba_tecnica.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prueba_tecnica.entity.Session;
import com.example.prueba_tecnica.entity.Usuario;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findFirstByUsuarioOrderByFechaIngresoDesc(Usuario usuario);
    Optional<Session> findByUsuarioAndFechaCierreIsNull(Usuario usuario);
}