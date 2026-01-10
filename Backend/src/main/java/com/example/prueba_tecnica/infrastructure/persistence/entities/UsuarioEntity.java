package com.example.prueba_tecnica.infrastructure.persistence.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(name = "session_active", length = 1)
    private String sessionActive; // 'S' o 'N'

    private String status; // 'ACTIVO', 'BLOQUEADO'

    private Integer intentos;

    // Relación con Persona (Requisito: Un usuario pertenece a una persona)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    private PersonaEntity persona;

    // Relación con Roles (Para el menú dinámico y permisos)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<RolEntity> roles;
}
