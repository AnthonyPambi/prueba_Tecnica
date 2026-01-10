package com.example.prueba_tecnica.infrastructure.persistence.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "rol_name", nullable = false, unique = true)
    private String rolName;

    // Se cambió OpcionesEntity por OpcionEntity para que coincida con tu archivo
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rol_opciones",
        joinColumns = @JoinColumn(name = "id_rol"),
        inverseJoinColumns = @JoinColumn(name = "id_opcion")
    )
    private List<OpcionEntity> opciones; 
}
