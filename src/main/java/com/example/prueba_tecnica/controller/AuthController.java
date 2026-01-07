package com.example.prueba_tecnica.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba_tecnica.entity.Usuario;
import com.example.prueba_tecnica.repository.UsuarioRepository;
import com.example.prueba_tecnica.service.AuthService;
import com.example.prueba_tecnica.service.SessionService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permite conexión con el frontend (React)
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthService authService, SessionService sessionService, UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String identifier = loginRequest.get("identifier"); // Puede ser username o mail
        String password = loginRequest.get("password");

        // 1. Ejecutar lógica de autenticación (bloqueo e intentos)
        String resultado = authService.login(identifier, password);

        if ("Login exitoso".equals(resultado)) {
            // Si el login es exitoso, devolvemos los datos del usuario para el Dashboard
            Optional<Usuario> userOpt = usuarioRepository.findByUserNameOrMail(identifier, identifier);
            return ResponseEntity.ok(userOpt.get());
        } else {
            // Si falla por bloqueo, sesión activa o contraseña, devolvemos 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", resultado));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> logoutRequest) {
        String username = logoutRequest.get("username");
        
        Optional<Usuario> userOpt = usuarioRepository.findByUserName(username);
        if (userOpt.isPresent()) {
            // 2. Registrar fecha de cierre y poner SessionActive en 'N'
            sessionService.registrarCierre(userOpt.get());
            return ResponseEntity.ok(Map.of("message", "Sesión cerrada correctamente"));
        }
        
        return ResponseEntity.badRequest().body(Map.of("message", "Usuario no encontrado"));
    }
}