package com.example.prueba_tecnica.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSession;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso; // [cite: 116, 117]

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre; // [cite: 118, 119]

    @ManyToOne
    @JoinColumn(name = "usuarios_idUsuario", nullable = false)
    private Usuario usuario; // [cite: 120, 122]

    // --- GETTERS Y SETTERS ---

    public Integer getIdSession() { return idSession; }
    public void setIdSession(Integer idSession) { this.idSession = idSession; }

    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDateTime fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}