package com.example.prueba_tecnica.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "rol")
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();

}
