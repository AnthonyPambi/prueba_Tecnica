package com.example.prueba_tecnica.infrastructure.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.domain.models.Usuario;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository; // IMPORTANTE: Inyecta el Puerto

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    // Cambiamos UsuarioRepository (Infra) por IUsuarioRepository (Domain Port)
    private final IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Usamos el método flexible que definimos para buscar por Mail o Username
        Usuario usuario = usuarioRepository.findByUserNameOrMail(identifier, identifier)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con: " + identifier));
      
        return new CustomUserDetails(usuario);
    }
}