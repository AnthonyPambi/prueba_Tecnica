package com.example.prueba_tecnica.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "opciones")
@Data
public class OpcionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_opcion")
    private Integer idOpcion;

    @Column(name = "nombre_opcion", nullable = false)
    private String nombreOpcion;

    private String icono;
    
    @Column(name = "path_url")
    private String pathUrl;
}