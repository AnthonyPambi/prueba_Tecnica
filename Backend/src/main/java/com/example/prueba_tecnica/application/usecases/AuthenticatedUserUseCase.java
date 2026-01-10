package com.example.prueba_tecnica.application.usecases;

import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.application.service.AuthService;
import com.example.prueba_tecnica.application.service.SessionService;
import com.example.prueba_tecnica.domain.dtos.LoginRequestDTO;
import com.example.prueba_tecnica.domain.dtos.UserResponseDTO;
import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;

@Service
public class AuthenticatedUserUseCase {

    private final AuthService authService;
    private final SessionService sessionService;
    private final IUsuarioRepository usuarioRepository;

    public AuthenticatedUserUseCase(AuthService authService, SessionService sessionService, IUsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.usuarioRepository = usuarioRepository;
    }

    public UserResponseDTO execute(LoginRequestDTO loginRequest) {
        // 1. Delegar validación (Lógica de intentos y bloqueo dentro de AuthService)
        String resultadoAuth = authService.login(loginRequest.getIdentifier(), loginRequest.getPassword());

        if (!"Login exitoso".equals(resultadoAuth)) {
            // Nota: En un entorno real, aquí lanzarías una excepción personalizada (ej. UserBlockedException)
            throw new RuntimeException(resultadoAuth); 
        }

        // 2. Recuperar el usuario usando el identificador (mail o username)
        // Ajustado para usar el método que definimos en IUsuarioRepository
        Usuario usuario = usuarioRepository.findByUserNameOrMail(loginRequest.getIdentifier(), loginRequest.getIdentifier())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado tras autenticación"));

        // 3. Registrar el inicio de sesión (Esto cambia SessionActive a 'S' y crea el log)
        sessionService.registrarInicio(usuario);

        // 4. Mapear al DTO de respuesta (Seguridad: No incluimos el password)
        return new UserResponseDTO(
            usuario.getUserName(),
            usuario.getMail(),
            "S",
            usuario.getStatus()
        );
    }
}