package com.example.prueba_tecnica.domain.models;

public class RolOpciones {
    private Integer idOpcion;
    private String nombreOpcion;
    private String icono;
    private String path;

    // Constructores
    public RolOpciones() {}

    public RolOpciones(Integer idOpcion, String nombreOpcion) {
        this.idOpcion = idOpcion;
        this.nombreOpcion = nombreOpcion;
    }

    // Getters y Setters
    public Integer getIdOpcion() { return idOpcion; }
    public void setIdOpcion(Integer idOpcion) { this.idOpcion = idOpcion; }
    public String getNombreOpcion() { return nombreOpcion; }
    public void setNombreOpcion(String nombreOpcion) { this.nombreOpcion = nombreOpcion; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getIcono() { return icono; }
    public void setIcono(String icono) { this.icono = icono; }
}