package com.example.prueba_tecnica.domain.models;

public class UsuarioRol {
    private Integer id; 
    private Usuario usuario;
    private Rol rol;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}
