package com.example.prueba_tecnica.application.usecases;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.prueba_tecnica.application.service.UsuarioService; // Solo necesitamos este
import com.example.prueba_tecnica.domain.dtos.UserRequestDTO;
import com.example.prueba_tecnica.domain.dtos.UserResponseDTO;
import com.example.prueba_tecnica.domain.models.Persona;
import com.example.prueba_tecnica.domain.models.Usuario;

@Service
public class CreateUserCase {

    private final UsuarioService usuarioService;

    public CreateUserCase(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    public UserResponseDTO execute(UserRequestDTO request) {
        // 1. Validaciones de negocio (Requerimientos 2 y 3 del PDF)
        validarSeguridad(request);

        // 2. Mapeo de DTO a Objetos de Dominio
        Persona persona = new Persona();
        persona.setNombres(request.getNombres());
        persona.setApellidos(request.getApellidos());
        persona.setIdentificacion(request.getIdentificacion());
        persona.setFechaNacimiento(request.getFechaNacimiento());

        Usuario usuario = new Usuario();
        usuario.setUserName(request.getUserName());
        usuario.setPassword(request.getPassword());
        usuario.setPersona(persona); // Relación 1:1

        // 3. Persistencia delegada al servicio (Generación de Mail incluida)
        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuario);

        // 4. Mapeo de salida
        UserResponseDTO response = new UserResponseDTO();
        response.setUserName(usuarioGuardado.getUserName());
        response.setMail(usuarioGuardado.getMail());
        response.setStatus(usuarioGuardado.getStatus());

        return response;
    }

    private void validarSeguridad(UserRequestDTO request) {
        // Validación Password: 8 chars, 1 Mayus, 1 Signo, sin espacios
        String pass = request.getPassword();
        if (pass == null || pass.length() < 8 || !pass.matches(".*[A-Z].*") || 
            !pass.matches(".*[!@#$%^&*(),.?\":{}|<>].*") || pass.contains(" ")) {
            throw new RuntimeException("La contraseña no cumple con los requisitos de seguridad.");
        }
        
        // Validación Username: Sin signos, 1 número, 1 Mayus
        String user = request.getUserName();
        if (user == null || !user.matches("^[a-zA-Z0-9]*$") || 
            !user.matches(".*[0-9].*") || !user.matches(".*[A-Z].*")) {
             throw new RuntimeException("El nombre de usuario no cumple con los parámetros exigidos.");
        }
    }
}
