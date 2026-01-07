package com.example.prueba_tecnica.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba_tecnica.entity.Session;
import com.example.prueba_tecnica.entity.Usuario;
import com.example.prueba_tecnica.repository.SessionRepository;
import com.example.prueba_tecnica.repository.UsuarioRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UsuarioRepository usuarioRepository;

    public SessionService(SessionRepository sessionRepository, UsuarioRepository usuarioRepository) {
        this.sessionRepository = sessionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Registrar inicio de sesión (Requisito II)
    @Transactional
    public void registrarInicio(Usuario usuario) {
        Session session = new Session();
        session.setUsuario(usuario);
        session.setFechaIngreso(LocalDateTime.now());
        sessionRepository.save(session);
        
        // Actualizar estado del usuario a sesión activa (Requisito I)
        usuario.setSessionActive("S");
        usuarioRepository.save(usuario);
    }

    // Registrar cierre de sesión (Requisito II)
    @Transactional
    public void registrarCierre(Usuario usuario) {
        // Buscar la sesión que no tiene fecha de cierre
        Optional<Session> sessionOpt = sessionRepository.findByUsuarioAndFechaCierreIsNull(usuario);
        
        sessionOpt.ifPresent(session -> {
            session.setFechaCierre(LocalDateTime.now());
            sessionRepository.save(session);
        });

        // Cambiar estado del usuario a inactivo
        usuario.setSessionActive("N");
        usuarioRepository.save(usuario);
    }

    // Obtener última sesión para la pantalla de bienvenida (Requisito VI)
    public Optional<Session> obtenerUltimaSesion(Usuario usuario) {
        return sessionRepository.findFirstByUsuarioOrderByFechaIngresoDesc(usuario);
    }
}