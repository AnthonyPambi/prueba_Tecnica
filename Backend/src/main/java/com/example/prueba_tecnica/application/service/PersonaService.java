package com.example.prueba_tecnica.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.domain.models.Persona;
import com.example.prueba_tecnica.domain.ports.IPersonaRepository;

@Service
public class PersonaService {

    private final IPersonaRepository personaRepository;

    public PersonaService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // 1. Registro Individual
    public String registrarPersona(Persona persona) {
        // Lógica de negocio: Regla de unicidad (No pueden haber dos personas con el mismo ID)
        if (personaRepository.findByIdentificacion(persona.getIdentificacion()).isPresent()) {
            return "Error: El número de identificación ya está registrado.";
        }

        // Nota: Las validaciones de formato (letras y números repetidos) 
        // ya fueron validadas por el Controller mediante el DTO y @Valid

        personaRepository.save(persona); 
        return "Persona registrada exitosamente.";
    }

    // 2. Carga Masiva (Requisito V)
    public void guardarLote(List<Persona> personas) {
        for (Persona p : personas) {
            // Evitamos duplicados en la carga masiva
            if (personaRepository.findByIdentificacion(p.getIdentificacion()).isEmpty()) {
                personaRepository.save(p);
            }
        }
    }

    public List<Persona> listarTodas() {
        return personaRepository.findAll();
    }
}