package com.example.prueba_tecnica.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "RolOpciones")
public class RolOpciones{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idOpcion")
    
    private Integer idOpcion; 

    @NotBlank(message = "El nombre de la opción es obligatorio")
    @Size(max = 50, message = "El nombre de la opción no puede exceder los 50 caracteres")
    @Column(name = "NombreOpcion", length = 50, nullable = false)
    private String nombreOpcion; 


    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

}