package com.example.prueba_tecnica.application.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.prueba_tecnica.domain.models.Rol;
import com.example.prueba_tecnica.domain.models.RolOpciones;
import com.example.prueba_tecnica.domain.ports.IRolRepository;

@Service
public class RolService {

    private final IRolRepository rolRepository;

    public RolService(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // 1. Obtener todos los roles (ADMIN, EXTERNO, etc.)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    // 2. Obtener opciones de menú (Crucial para el Menú Dinámico del Frontend)
    public List<RolOpciones> obtenerPermisosPorRol(Integer idRol) {
        return rolRepository.findOptionsByRolId(idRol);
    }

    // 3. Vincular Rol a Usuario
    public void asignarRolUsuario(Integer idUsuario, Integer idRol) {
        rolRepository.linkUserToRole(idUsuario, idRol);
    }
}