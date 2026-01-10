package com.example.prueba_tecnica.infrastructure.repository;

import org.springframework.stereotype.Repository;
import com.example.prueba_tecnica.domain.models.Persona;
import com.example.prueba_tecnica.domain.ports.IPersonaRepository;
import com.example.prueba_tecnica.infrastructure.persistence.jpa.JpaPersonaRepository;
import com.example.prueba_tecnica.infrastructure.persistence.entities.PersonaEntity;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import java.util.List; // AGREGAR ESTO: Importación necesaria para List
import java.util.stream.Collectors; // AGREGAR ESTO: Para que el .collect sea más limpio

@Repository
@RequiredArgsConstructor
public class PersonaRepositoryImpl implements IPersonaRepository {

    private final JpaPersonaRepository jpaRepository;

    @Override
    public Optional<Persona> findById(Integer id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Persona> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIdentificacion(String identificacion) {
        // Importante para cumplir con la validación de no duplicados
        return jpaRepository.findByIdentificacion(identificacion).isPresent();
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity entity = toEntity(persona);
        PersonaEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Persona> findByIdentificacion(String identificacion) {
        return jpaRepository.findByIdentificacion(identificacion)
                .map(this::toDomain);
    }

    // --- MAPPERS ---
    
    private Persona toDomain(PersonaEntity entity) {
        if (entity == null) return null;
        Persona model = new Persona();
        model.setIdPersona(entity.getIdPersona());
        model.setNombres(entity.getNombres());
        model.setApellidos(entity.getApellidos());
        model.setIdentificacion(entity.getIdentificacion());
        // El PDF requiere persistencia de datos personales
        model.setFechaNacimiento(entity.getFechaNacimiento());
        return model;
    }

    private PersonaEntity toEntity(Persona model) {
        if (model == null) return null;
        PersonaEntity entity = new PersonaEntity();
        entity.setIdPersona(model.getIdPersona());
        entity.setNombres(model.getNombres());
        entity.setApellidos(model.getApellidos());
        entity.setIdentificacion(model.getIdentificacion());
        entity.setFechaNacimiento(model.getFechaNacimiento());
        return entity;
    }
}
