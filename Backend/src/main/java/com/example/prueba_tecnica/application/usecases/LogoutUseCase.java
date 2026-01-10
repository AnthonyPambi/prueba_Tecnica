package com.example.prueba_tecnica.application.usecases;

import org.springframework.stereotype.Service;
import com.example.prueba_tecnica.application.service.SessionService;
import com.example.prueba_tecnica.domain.models.Usuario;

@Service
public class LogoutUseCase {
    private final SessionService sessionService;

    public LogoutUseCase(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void execute(Usuario usuario) {
        // Registra fecha_egreso y pone SessionActive = 'N'
        sessionService.registrarCierre(usuario);
    }
}