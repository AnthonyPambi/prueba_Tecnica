package com.example.prueba_tecnica.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.prueba_tecnica.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   
    Optional<Usuario> findByUserName(String userName);
    
    boolean existsByMail(String mail);

    Optional<Usuario> findByUserNameOrMail(String userName, String mail);

    Optional<Usuario> findByMail(String mail);

    }
   