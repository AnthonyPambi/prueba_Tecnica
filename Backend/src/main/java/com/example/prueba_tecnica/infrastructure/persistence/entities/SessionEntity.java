package com.example.prueba_tecnica.infrastructure.persistence.entities;

import java.util.Date; // Usar Date para ser consistentes con el resto del proyecto
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sessions")
@Data
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Integer idSession;

    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso; // Cambiado a Date para compatibilidad

    @Column(name = "fecha_egreso")
    private Date fechaEgreso;

    // CORRECCIÓN CLAVE: Relación real con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario; 
}
