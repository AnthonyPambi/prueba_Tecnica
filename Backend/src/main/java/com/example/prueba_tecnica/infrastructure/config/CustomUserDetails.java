package com.example.prueba_tecnica.infrastructure.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.prueba_tecnica.domain.models.Usuario;

public class CustomUserDetails implements UserDetails {
    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    // Mapeo de Roles del Dominio a GrantedAuthority de Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRolName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUserName(); 
    }

    // Lógica de Bloqueo: Conectamos con el campo 'Status' del dominio
    @Override
    public boolean isAccountNonLocked() {
        return !"BLOQUEADO".equals(usuario.getStatus());
    }

    // Lógica de Habilitación: Podrías usar el status para desactivar cuentas
    @Override
    public boolean isEnabled() {
        return "ACTIVO".equals(usuario.getStatus());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}