package com.example.prueba_tecnica.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "Rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol") 
    private Integer idRol;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 50)
    @Column(name = "RolName", nullable = false, length = 50) 
    private String rolName;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rol_rol_Opciones", 
        joinColumns = @JoinColumn(name = "Rol_idRol"), 
        inverseJoinColumns = @JoinColumn(name = "RolOpciones_idOpcion") 
    )
    private List<RolOpciones> opciones = new ArrayList<>();

    
    @OneToMany(mappedBy = "rol")
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();


    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public List<RolOpciones> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<RolOpciones> opciones) {
        this.opciones = opciones;
    }

    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
}
