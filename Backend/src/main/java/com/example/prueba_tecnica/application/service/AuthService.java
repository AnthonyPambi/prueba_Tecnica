package com.example.prueba_tecnica.application.service;

import org.springframework.stereotype.Service;
import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;
import java.util.Optional;

@Service
public class AuthService {

    private final IUsuarioRepository usuarioRepository;

    public AuthService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String login(String identifier, String password) {
        // Punto III: Buscar por username o mail [cite: 38-39]
        Optional<Usuario> userOpt = usuarioRepository.findByUserNameOrMail(identifier, identifier);

        if (userOpt.isEmpty()) {
            return "Usuario no encontrado";
        }

        Usuario usuario = userOpt.get();

        // Punto IV: Verificar si está bloqueado [cite: 41-44]
        if ("BLOQUEADO".equals(usuario.getStatus())) {
            return "Usuario bloqueado";
        }

        // Lógica de validación de password
        if (usuario.getPassword().equals(password)) {
            // Login exitoso: Resetear intentos y activar sesión
            usuario.setIntentos(0);
            usuario.setSessionActive("S");
            usuarioRepository.save(usuario);
            return "Login exitoso";
        } else {
            // Fallo de contraseña: Incrementar intentos
            int nuevosIntentos = usuario.getIntentos() + 1;
            usuario.setIntentos(nuevosIntentos);

            // Regla: A los 3 intentos fallidos se bloquea [cite: 41-42]
            if (nuevosIntentos >= 3) {
                usuario.setStatus("BLOQUEADO");
                usuarioRepository.save(usuario);
                return "Usuario bloqueado por superar 3 intentos";
            }

            usuarioRepository.save(usuario);
            return "Contraseña incorrecta. Intento " + nuevosIntentos + " de 3";
        }
    }
}