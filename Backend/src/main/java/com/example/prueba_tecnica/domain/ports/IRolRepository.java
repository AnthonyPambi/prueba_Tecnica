package com.example.prueba_tecnica.domain.ports;

import java.util.List;

import java.util.Optional;

import com.example.prueba_tecnica.domain.models.Rol;

import com.example.prueba_tecnica.domain.models.RolOpciones;

public interface IRolRepository {

    // Recupera todos los roles (ADMIN, EXTERNO) para llenar combos en el Front
    List<Rol> findAll();

    Optional<Rol> findByRolName(String rolName);

    // Busca un rol por su ID
    Optional<Rol> findById(Integer idRol);

    // Obtiene la lista de opciones (menú) asociadas a un rol específico
    // Requerido para el Menú Dinámico
    List<RolOpciones> findOptionsByRolId(Integer idRol);

    // Vincula un usuario con un rol específico
    // En Infrastructure, esto puede llamar a un Stored Procedure
    void linkUserToRole(Integer idUsuario, Integer idRol);

}
