package com.example.prueba_tecnica.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.entity.Session;
import com.example.prueba_tecnica.entity.Usuario;
import com.example.prueba_tecnica.repository.SessionRepository;
import com.example.prueba_tecnica.repository.UsuarioRepository;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    
    private final SessionRepository sessionRepository;

    public AuthService(UsuarioRepository usuarioRepository, SessionRepository sessionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.sessionRepository = sessionRepository;
    }
    public String login(String identificador, String password) {
        // 1. El inicio de sesión puede ser con correo o nombre de usuario 
        Optional<Usuario> userOpt = usuarioRepository.findByUserNameOrMail(identificador, identificador);

        if (userOpt.isEmpty()) {
            return "Usuario no encontrado";
        }

        Usuario usuario = userOpt.get();

        // 2. Verificar si el usuario ya está bloqueado 
        if ("BLOQUEADO".equals(usuario.getStatus())) {
            return "Usuario bloqueado por múltiples intentos fallidos";
        }

        // 3. Validar si ya tiene una sesión activa 
        if ("S".equals(usuario.getSessionActive())) {
            return "Ya existe una sesión activa para este usuario"; 
        }

        // 4. Validar contraseña
        if (usuario.getPassword().equals(password)) {
            resetearIntentosYActivarSesion(usuario);
            registrarSesion(usuario); // 
            return "Login exitoso";
        } else {
            return manejarIntentoFallido(usuario); 
        }
    }
    private String manejarIntentoFallido(Usuario usuario) {
        // En una implementación real, 'intentos' debería ser un campo en la tabla usuarios
        int intentosActuales = usuario.getIntentos() + 1;
        usuario.setIntentos(intentosActuales);

        if (intentosActuales >= 3) {
            usuario.setStatus("BLOQUEADO"); 
            usuarioRepository.save(usuario);
            return "Contraseña incorrecta. Usuario bloqueado tras 3 intentos.";
        }

        usuarioRepository.save(usuario);
        return "Contraseña incorrecta. Intento " + intentosActuales + " de 3.";
    }

    private void resetearIntentosYActivarSesion(Usuario usuario) {
        usuario.setIntentos(0);
        usuario.setSessionActive("S"); 
        usuarioRepository.save(usuario);
    }

    private void registrarSesion(Usuario usuario) {
        Session session = new Session();
        session.setUsuario(usuario);
        session.setFechaIngreso(LocalDateTime.now()); // 
        sessionRepository.save(session);
    }
    
}