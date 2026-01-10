package com.example.prueba_tecnica.domain.ports;

import java.util.Optional;

import com.example.prueba_tecnica.domain.models.Session;

public interface ISessionRepository {
    Session save(Session session);

    // Para cerrar la sesión actual (Logout)
    Optional<Session> findActiveSessionByUsuarioId(Integer idUsuario);

    // Para mostrar la última conexión en el Dashboard
    Optional<Session> findLastSessionByUsuarioId(Integer idUsuario);
}
