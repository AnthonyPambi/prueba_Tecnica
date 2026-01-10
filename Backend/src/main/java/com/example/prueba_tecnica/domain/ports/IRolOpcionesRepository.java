package com.example.prueba_tecnica.domain.ports;

import com.example.prueba_tecnica.domain.models.RolOpciones;
import java.util.List;

public interface IRolOpcionesRepository {

    /**
     * Busca todas las opciones de menú asignadas a un rol específico.
     * Requerido para la construcción del menú dinámico en el Dashboard.
     *
     */
    List<RolOpciones> findByRolId(Integer idRol);

    /**
     * Opcional: Para cumplir con el CRUD básico de todas las tablas solicitado.
     *
     */
    List<RolOpciones> findAll();
}
