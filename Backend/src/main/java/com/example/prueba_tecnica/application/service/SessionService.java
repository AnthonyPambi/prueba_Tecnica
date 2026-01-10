package com.example.prueba_tecnica.application.service;

import java.util.Date; // Cambiado a java.util.Date
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba_tecnica.domain.models.Session;
import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.ISessionRepository;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;

@Service
public class SessionService {

    private final ISessionRepository sessionRepository;
    private final IUsuarioRepository usuarioRepository;

    public SessionService(ISessionRepository sessionRepository, IUsuarioRepository usuarioRepository) {
        this.sessionRepository = sessionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void registrarInicio(Usuario usuario) {
        Session session = new Session();
        session.setUsuario(usuario);
        session.setFechaIngreso(new Date()); // Usando java.util.Date
        sessionRepository.save(session); 
        
        // Registro de sesión activa para control de sesión única
        usuario.setSessionActive("S");
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void registrarCierre(Usuario usuario) {
        // Buscamos la sesión usando el nombre del atributo corregido: fechaEgreso
        sessionRepository.findActiveSessionByUsuarioId(usuario.getIdUsuario())
            .ifPresent(session -> {
                session.setFechaEgreso(new Date()); // Nombre exacto de tu setter
                sessionRepository.save(session);
            });

        // Liberar al usuario para que SessionActive sea 'N'
        usuario.setSessionActive("N");
        usuarioRepository.save(usuario);
    }

    public Optional<Session> obtenerUltimaSesion(Usuario usuario) {
        return sessionRepository.findLastSessionByUsuarioId(usuario.getIdUsuario());
    }
}