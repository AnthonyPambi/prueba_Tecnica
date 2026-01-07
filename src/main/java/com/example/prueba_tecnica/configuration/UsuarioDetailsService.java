package com.example.prueba_tecnica.configuration;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.entity.Usuario;
import com.example.prueba_tecnica.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {
        private final UsuarioRepository usuarioRepositorio;
        @Override
        public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
                Usuario usuario = usuarioRepositorio.findByMail(mail)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
              
                return new CustomUserDetails(usuario);
        }
}