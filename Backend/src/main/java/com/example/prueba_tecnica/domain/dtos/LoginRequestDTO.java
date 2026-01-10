package com.example.prueba_tecnica.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "El identificador (usuario o correo) es obligatorio")
    private String identifier; // Puede recibir el UserName o el Mail

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Constructores
    public LoginRequestDTO() {}

    public LoginRequestDTO(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    // Getters y Setters
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
