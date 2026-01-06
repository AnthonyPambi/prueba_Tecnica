package com.example.prueba_tecnica.entity;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^(?!.*(\\d)\\1{3})\\d{10}$", message = "Debe tener 10 dígitos, solo números y no repetir un número 4 veces seguidas")
    @Column(nullable = false, unique = true, length = 10)
    private String identificacion;

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 8, max = 20, message = "Debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]+$", message = "Debe contener al menos una mayúscula, un número y no tener signos")
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean estado;

    private String status;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> usuarioRoles = new ArrayList<>();

}
