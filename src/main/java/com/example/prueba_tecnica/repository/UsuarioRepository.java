package com.example.prueba_tecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    

}
