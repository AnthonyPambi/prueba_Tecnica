package com.example.prueba_tecnica.domain.models;

import java.util.Date;

public class Session {
    private Integer idSession;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private Usuario usuario;

    // Getters y Setters
    public Integer getIdSession() { return idSession; }
    public void setIdSession(Integer idSession) { this.idSession = idSession; }

    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public Date getFechaEgreso() { return fechaEgreso; }
    public void setFechaEgreso(Date fechaEgreso) { this.fechaEgreso = fechaEgreso; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}