package com.example.prueba_tecnica.domain.dtos;

import java.time.LocalDateTime;

public class SessionResponseDTO {

    private String userName;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaCierre;
    private int intentosFallidos;

    // Constructores
    public SessionResponseDTO() {}

    public SessionResponseDTO(String userName, LocalDateTime fechaIngreso, LocalDateTime fechaCierre, int intentosFallidos) {
        this.userName = userName;
        this.fechaIngreso = fechaIngreso;
        this.fechaCierre = fechaCierre;
        this.intentosFallidos = intentosFallidos;
    }

    // Getters y Setters
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }

    public int getIntentosFallidos() { return intentosFallidos; }
    public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }
}