package com.example.prueba_tecnica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.entity.Persona;
import com.example.prueba_tecnica.repository.PersonaRepository;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // 1. Registro Individual con validaciones del documento
    public String registrarPersona(Persona persona) {
        // Validar que la identificación no exista ya
        if (personaRepository.findByIdentificacion(persona.getIdentificacion()).isPresent()) {
            return "Error: El número de identificación ya está registrado.";
        }

        // Validar que solo contenga letras en nombres y apellidos
        if (!persona.getNombres().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$") || 
            !persona.getApellidos().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return "Error: Nombres y apellidos solo deben contener letras.";
        }

        // Validar la regla de no repetir 4 números seguidos
        if (persona.getIdentificacion().matches(".*(\\d)\\1{3}.*")) {
            return "Error: La identificación no puede tener 4 números seguidos iguales.";
        }

        personaRepository.save(persona);
        return "Persona registrada exitosamente.";
    }

    // 2. Carga Masiva (Estructura lógica para Excel)
    public void guardarLote(List<Persona> personas) {
        // Aquí se procesaría la lista que viene del Excel
        for (Persona p : personas) {
            // Solo guardamos si pasa las validaciones básicas
            if (!personaRepository.findByIdentificacion(p.getIdentificacion()).isPresent()) {
                personaRepository.save(p);
            }
        }
    }

    public List<Persona> listarTodas() {
        return personaRepository.findAll();
    }
}