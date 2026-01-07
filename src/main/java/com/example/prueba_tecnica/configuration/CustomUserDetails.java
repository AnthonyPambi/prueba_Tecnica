package com.example.prueba_tecnica.configuration;



import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.prueba_tecnica.entity.Usuario;
public class CustomUserDetails implements UserDetails {
    private final Usuario usuario;
    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getNombre() {
        return usuario.getUserName(); 
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolName()))
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
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public Usuario getUsuario() {
    return this.usuario;
}
}