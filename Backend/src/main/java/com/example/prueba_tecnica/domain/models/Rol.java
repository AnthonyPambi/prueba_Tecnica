package com.example.prueba_tecnica.domain.models;

import java.util.List;

public class Rol {
    private Integer idRol;
    private String rolName;
    private List<RolOpciones> opciones;

    // Getters y Setters
    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }
    public String getRolName() { return rolName; }
    public void setRolName(String rolName) { this.rolName = rolName; }
    public List<RolOpciones> getOpciones() { return opciones; }
    public void setOpciones(List<RolOpciones> opciones) { this.opciones = opciones; }
}
