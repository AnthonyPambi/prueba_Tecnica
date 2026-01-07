package com.example.prueba_tecnica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    
    // Este método es vital para asignar el rol "ADMINISTRADOR" 
    // o "USUARIO" durante el registro o login.
    Optional<Rol> findByRolName(String rolName);
}