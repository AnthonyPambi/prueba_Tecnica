package com.example.prueba_tecnica.infrastructure.repository;

import org.springframework.stereotype.Repository;
import com.example.prueba_tecnica.domain.models.Session;
import com.example.prueba_tecnica.domain.ports.ISessionRepository;
import com.example.prueba_tecnica.infrastructure.persistence.jpa.JpaSessionRepository;
import com.example.prueba_tecnica.infrastructure.persistence.entities.SessionEntity;
import com.example.prueba_tecnica.infrastructure.persistence.entities.UsuarioEntity;

import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SessionRepositoryImpl implements ISessionRepository {

    private final JpaSessionRepository jpaSessionRepository;

    @Override
    public Session save(Session session) {
        SessionEntity entity = toEntity(session);
        return toDomain(jpaSessionRepository.save(entity));
    }

    @Override
    public Optional<Session> findActiveSessionByUsuarioId(Integer idUsuario) {
        // Busca la sesión que no tiene fecha de egreso (sesión abierta)
        return jpaSessionRepository.findFirstByUsuario_IdUsuarioAndFechaEgresoIsNull(idUsuario)
                .map(this::toDomain);
    }

    @Override
    public Optional<Session> findLastSessionByUsuarioId(Integer idUsuario) {
        // Busca la última sesión registrada para la auditoría
        return jpaSessionRepository.findFirstByUsuario_IdUsuarioOrderByFechaIngresoDesc(idUsuario)
                .map(this::toDomain);
    }

    private Session toDomain(SessionEntity entity) {
        if (entity == null) return null;
        Session model = new Session();
        model.setIdSession(entity.getIdSession());
        model.setFechaIngreso(entity.getFechaIngreso());
        model.setFechaEgreso(entity.getFechaEgreso());
        return model;
    }

    private SessionEntity toEntity(Session model) {
        if (model == null) return null;
        SessionEntity entity = new SessionEntity();
        entity.setIdSession(model.getIdSession());
        entity.setFechaIngreso(model.getFechaIngreso());
        entity.setFechaEgreso(model.getFechaEgreso());
        
        if (model.getUsuario() != null) {
            UsuarioEntity userEntity = new UsuarioEntity();
            userEntity.setIdUsuario(model.getUsuario().getIdUsuario());
            entity.setUsuario(userEntity);
        }
        return entity;
    }
}
