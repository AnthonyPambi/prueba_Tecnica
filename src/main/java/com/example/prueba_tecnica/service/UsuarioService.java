package com.example.prueba_tecnica.service;

import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.entity.Usuario;
import com.example.prueba_tecnica.repository.PersonaRepository;
import com.example.prueba_tecnica.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
    }

   
    public Usuario registrarUsuario(Usuario usuario) {
        // Aquí puedes agregar la lógica de generar el correo antes de guardar
        if (usuario.getMail() == null || usuario.getMail().isEmpty()) {
            // Suponiendo que el usuario trae un objeto Persona asociado
            String correo = generarCorreo("nombre", "apellido"); 
            usuario.setMail(correo);
        }
        return usuarioRepository.save(usuario);
    }

    public String generarCorreo(String nombres, String apellidos) {
        String[] nombresArray = nombres.trim().split("\\s+");
        String[] apellidosArray = apellidos.trim().split("\\s+");

        String letraNombre = normalizar(nombresArray[0]).substring(0, 1);
        String primerApellido = normalizar(apellidosArray[0]);

        String letraSegundoApellido = "";
        if (apellidosArray.length > 1) {
            letraSegundoApellido = normalizar(apellidosArray[1]).substring(0, 1);
        }

        return letraNombre + primerApellido + letraSegundoApellido + "@mail.com";
    }

    private String normalizar(String texto) {
        return texto.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n");
    }
}