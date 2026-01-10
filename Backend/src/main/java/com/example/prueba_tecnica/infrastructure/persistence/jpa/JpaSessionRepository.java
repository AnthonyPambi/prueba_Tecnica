package com.example.prueba_tecnica.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prueba_tecnica.infrastructure.persistence.entities.SessionEntity;

public interface JpaSessionRepository extends JpaRepository<SessionEntity, Integer> {

    /**
     * Busca la última sesión de un usuario usando la relación del objeto Usuario.
     * El guion bajo (_) le indica a JPA que entre al objeto 'usuario' y busque su 'idUsuario'.
     * Cumple con el requerimiento de auditoría de última conexión.
     */
    Optional<SessionEntity> findFirstByUsuario_IdUsuarioOrderByFechaIngresoDesc(Integer idUsuario);
    

    Optional<SessionEntity> findFirstByUsuario_IdUsuarioAndFechaEgresoIsNull(Integer idUsuario);
    /**
     * Obtiene el historial de sesiones de un usuario.
     * Importante: El nombre debe coincidir con el atributo en SessionEntity (usuario).
     */
    List<SessionEntity> findAllByUsuario_IdUsuario(Integer idUsuario);
}
