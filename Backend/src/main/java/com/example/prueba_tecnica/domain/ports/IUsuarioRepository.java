package com.example.prueba_tecnica.domain.ports;

import java.util.List;
import java.util.Optional;

import com.example.prueba_tecnica.domain.models.Usuario;

public interface IUsuarioRepository {

    // Guarda o actualiza un usuario (Registro y actualización de intentos/status)
    Usuario save(Usuario usuario);

    // Para el Login: Busca por Username o por Mail
    Optional<Usuario> findByUserNameOrMail(String userName, String mail);

    // Para validaciones de duplicados durante el registro
    Optional<Usuario> findByUserName(String userName);
    Optional<Usuario> findByMail(String mail);
    boolean existsByUserName(String userName);
    // Para gestión administrativa y listados (sin devolver password en el DTO final)
    List<Usuario> findAll();

    // Para buscar por ID en procesos internos
    Optional<Usuario> findById(Integer idUsuario);
    
    // Verifica si existe para validaciones rápidas
   
    
}
