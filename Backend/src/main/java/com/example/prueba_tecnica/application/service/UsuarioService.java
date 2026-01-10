package com.example.prueba_tecnica.application.service;

import org.springframework.stereotype.Service;
import com.example.prueba_tecnica.domain.models.Persona;
import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        Persona persona = usuario.getPersona();
        
        // 1. Validar Identificación (Requerimiento 4 del PDF) 
        validarIdentificacion(persona.getIdentificacion());

        // 2. Generar correo automático (Requerimiento 1.1) [cite: 9-15]
        String correoBase = generarCorreoBase(persona.getNombres(), persona.getApellidos());
        String correoFinal = verificarYGenerarCorreoUnico(correoBase);
        
        usuario.setMail(correoFinal);
        usuario.setStatus("ACTIVO"); // Campo status según figura 1 [cite: 44]
        usuario.setSessionActive("N");
        usuario.setIntentos(0); 
        
        return usuarioRepository.save(usuario);
    }

    // Métodos para cumplir el CRUD básico exigido [cite: 7]
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    private void validarIdentificacion(String cedula) {
        // a. Debe tener 10 dígitos [cite: 28]
        // b. Solo números [cite: 29]
        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new RuntimeException("La identificación debe tener 10 dígitos numéricos");
        }

        // c. Validar que no tenga 4 números iguales seguidos 
        for (int i = 0; i < cedula.length() - 3; i++) {
            if (cedula.charAt(i) == cedula.charAt(i+1) && 
                cedula.charAt(i) == cedula.charAt(i+2) && 
                cedula.charAt(i) == cedula.charAt(i+3)) {
                throw new RuntimeException("La identificación no puede tener 4 números repetidos seguidos");
            }
        }
    }

    private String generarCorreoBase(String nombres, String apellidos) {
        String[] nombresArray = nombres.trim().split("\\s+");
        String[] apellidosArray = apellidos.trim().split("\\s+");

        String letraNombre = normalizar(nombresArray[0]).substring(0, 1);
        String primerApellido = normalizar(apellidosArray[0]);
        String letraSegundoApellido = (apellidosArray.length > 1) ? 
                                       normalizar(apellidosArray[1]).substring(0, 1) : "";

        return letraNombre + primerApellido + letraSegundoApellido;
    }

    private String verificarYGenerarCorreoUnico(String correoBase) {
        String dominio = "@mail.com";
        String correoPropuesto = correoBase + dominio;
        int contador = 1;

        while (usuarioRepository.findByMail(correoPropuesto).isPresent()) {
            correoPropuesto = correoBase + contador + dominio; // Punto 1.1 [cite: 15]
            contador++;
        }
        return correoPropuesto;
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        return texto.toLowerCase()
                .replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("ñ", "n");
    }
}