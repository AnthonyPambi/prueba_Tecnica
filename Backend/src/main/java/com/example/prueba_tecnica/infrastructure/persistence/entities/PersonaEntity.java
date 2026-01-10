package com.example.prueba_tecnica.infrastructure.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "persona") // El PDF la nombra "Persona" en singular [cite: 103]
@Data
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona") // Nombre exacto del diagrama [cite: 104]
    private Integer idPersona;

    @Column(name = "Nombres", nullable = false, length = 80) // El PDF pide 80 [cite: 107]
    private String nombres;

    @Column(name = "Apellidos", nullable = false, length = 80) // El PDF pide 80 [cite: 108]
    private String apellidos;

    @Column(name = "Identificacion", nullable = false, unique = true, length = 10) // El PDF pide 10 [cite: 109]
    private String identificacion;

    @Column(name = "FechaNacimiento") // Campo obligatorio según el PDF 
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
}