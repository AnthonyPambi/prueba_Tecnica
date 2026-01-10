package com.example.prueba_tecnica.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date; // 1. IMPORTANTE: Agregar este import

public class UserRequestDTO {

    private Date fechaNacimiento;

    // Validaciones para Persona
    @NotBlank(message = "Nombres son obligatorios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "Nombres solo deben contener letras")
    private String nombres;

    @NotBlank(message = "Apellidos son obligatorios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "Apellidos solo deben contener letras")
    private String apellidos;

    @NotBlank(message = "Identificación es obligatoria")
    @Pattern(regexp = "^[0-9]{10}$", message = "La identificación debe tener exactamente 10 dígitos")
    private String identificacion;

    // Validaciones para Usuario
    @NotBlank(message = "Nombre de usuario es obligatorio")
    @Size(min = 8, max = 20, message = "El usuario debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9]+$", 
             message = "El usuario debe tener al menos una mayúscula, un número y no tener signos")
    private String userName;

    @NotBlank(message = "Contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=\\S+$).{8,}$", 
             message = "La contraseña debe tener mínimo 8 caracteres, una mayúscula, un signo y no tener espacios")
    private String password;

    // Getters y Setters corregidos
    
    // 2. AGREGAR ESTOS DOS MÉTODOS (Fundamental para CreateUserCase)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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
}