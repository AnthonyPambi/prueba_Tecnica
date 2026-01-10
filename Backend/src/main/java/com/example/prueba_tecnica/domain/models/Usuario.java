package com.example.prueba_tecnica.domain.models;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {
    private Integer idUsuario;
    private Persona persona;
    private String userName;
    private String password; // Se mantiene en el modelo para lógica de auth, pero el DTO de salida lo ignora
    private String mail;
    private String sessionActive = "N";
    private String status = "ACTIVO";
    private Integer intentos = 0;
    private List<UsuarioRol> usuarioRoles;

    // Lógica de negocio dentro del modelo (opcional pero recomendada en DDD)
    public Set<Rol> getRoles() {
        if (usuarioRoles == null) return null;
        return usuarioRoles.stream()
                .map(UsuarioRol::getRol)
                .collect(Collectors.toSet());
    }

    // Getters y Setters...
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSessionActive() { return sessionActive; }
    public void setSessionActive(String sessionActive) { this.sessionActive = sessionActive; }
    public Integer getIntentos() { return intentos; }
    public void setIntentos(Integer intentos) { this.intentos = intentos; }
    public List<UsuarioRol> getUsuarioRoles() { return usuarioRoles; }
    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) { this.usuarioRoles = usuarioRoles; }
}
