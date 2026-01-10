package com.example.prueba_tecnica.infrastructure.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; // IMPORTANTE: Usar el Port

import com.example.prueba_tecnica.application.service.AuthService;
import com.example.prueba_tecnica.application.service.SessionService;
import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;
    private final IUsuarioRepository usuarioRepository; // CORRECCIÓN: Inyectar la Interfaz del puerto

    public AuthController(AuthService authService, SessionService sessionService, IUsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String identifier = loginRequest.get("username"); 
        String password = loginRequest.get("password");

        // Cumple Punto IV: Lógica de 3 intentos y bloqueo [cite: 41-42]
        String resultado = authService.login(identifier, password);

        if ("Login exitoso".equals(resultado)) {
            // Cumple Punto III: Login con correo o username 
            Usuario usuario = usuarioRepository.findByUserNameOrMail(identifier, identifier)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado post-auth"));
            
            return ResponseEntity.ok(usuario);
        } else {
            // Retorna el mensaje de error o bloqueo exigido [cite: 42-44]
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", resultado));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> logoutRequest) {
        String username = logoutRequest.get("username");
        
        Optional<Usuario> userOpt = usuarioRepository.findByUserName(username);
        if (userOpt.isPresent()) {
            // Cumple Punto II: Registrar cierres de sesión [cite: 36-37]
            sessionService.registrarCierre(userOpt.get());
            return ResponseEntity.ok(Map.of("message", "Sesión cerrada correctamente"));
        }
        
        return ResponseEntity.badRequest().body(Map.of("message", "Usuario no encontrado"));
    }
}