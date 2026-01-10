package com.example.prueba_tecnica.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.prueba_tecnica.domain.models.Rol;
import com.example.prueba_tecnica.domain.models.RolOpciones;
import com.example.prueba_tecnica.domain.ports.IRolRepository;
import com.example.prueba_tecnica.infrastructure.persistence.entities.RolEntity;
import com.example.prueba_tecnica.infrastructure.persistence.jpa.JpaRolRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RolRepositoryImpl implements IRolRepository {

    private final JpaRolRepository jpaRolRepository;

    @Override
    public List<Rol> findAll() {
        return jpaRolRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findByRolName(String rolName) {
        return jpaRolRepository.findByRolName(rolName)
                .map(this::toDomain);
    }

    @Override
    public Optional<Rol> findById(Integer idRol) {
        return jpaRolRepository.findById(idRol)
                .map(this::toDomain);
    }

    @Override
    public List<RolOpciones> findOptionsByRolId(Integer idRol) {
        // Ahora buscamos el Rol y sacamos sus opciones directamente
        return jpaRolRepository.findById(idRol)
                .map(entity -> entity.getOpciones().stream()
                    .map(opcion -> {
                        RolOpciones model = new RolOpciones();
                        model.setIdOpcion(opcion.getIdOpcion());
                        model.setNombreOpcion(opcion.getNombreOpcion());
                        model.setPath(opcion.getPathUrl());
                        model.setIcono(opcion.getIcono());
                        return model;
                    }).collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    @Override
    public void linkUserToRole(Integer idUsuario, Integer idRol) {
        // Implementación pendiente según necesidad
    }

    private Rol toDomain(RolEntity entity) {
        if (entity == null) return null;
        Rol model = new Rol();
        model.setIdRol(entity.getIdRol());
        model.setRolName(entity.getRolName());
        return model;
    }
}