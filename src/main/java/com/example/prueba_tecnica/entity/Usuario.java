package com.example.prueba_tecnica.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "Persona_idPersona2", nullable = false)
    private Persona persona;

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 8, max = 20, message = "Debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]+$", message = "Debe contener al menos una mayúscula, un número y no tener signos")
    @Column(name = "Username", nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "Debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]+$", message = "Debe contener al menos una mayúscula, un número y no tener signos")
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Column(name = "Mail", nullable = false, length = 120)
    private String mail;

    @Column(name = "SessionActive", length = 1)
    private String sessionActive = "N";

    @Column(name = "Status", length = 20)
    private String status = "ACTIVO";

    @Column(name = "intentos")
    private Integer intentos = 0;

    


    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();

    public Set<Rol> getRoles() {
        return usuarioRoles
                .stream()
                .map(UsuarioRol::getRol)
                .collect(java.util.stream.Collectors.toSet());
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getter y Setter para la relación con Persona (OBLIGATORIO) 
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(String sessionActive) {
        this.sessionActive = sessionActive;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

}
