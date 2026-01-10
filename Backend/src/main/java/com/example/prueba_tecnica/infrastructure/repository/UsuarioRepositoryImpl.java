package com.example.prueba_tecnica.infrastructure.repository;

import org.springframework.stereotype.Repository;
import com.example.prueba_tecnica.domain.models.*;
import com.example.prueba_tecnica.domain.ports.IUsuarioRepository;
import com.example.prueba_tecnica.infrastructure.persistence.jpa.JpaUsuarioRepository;
import com.example.prueba_tecnica.infrastructure.persistence.entities.*;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    @Override
    public Optional<Usuario> findByUserNameOrMail(String username, String mail) {
        return jpaUsuarioRepository.findByUserNameOrMail(username, mail)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findByMail(String mail) {
        return jpaUsuarioRepository.findByMail(mail)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findByUserName(String userName) {
        // Debes tener este método en tu JpaUsuarioRepository
        return jpaUsuarioRepository.findByUserName(userName)
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return jpaUsuarioRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Integer idUsuario) {
        return jpaUsuarioRepository.findById(idUsuario)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return jpaUsuarioRepository.existsByUserName(userName);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        UsuarioEntity saved = jpaUsuarioRepository.save(entity);
        return toDomain(saved);
    }

    // --- MAPPERS ---

    private Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        Usuario model = new Usuario();
        model.setIdUsuario(entity.getIdUsuario());
        model.setUserName(entity.getUserName());
        model.setPassword(entity.getPassword());
        model.setMail(entity.getMail());
        model.setSessionActive(entity.getSessionActive());
        model.setStatus(entity.getStatus());
        model.setIntentos(entity.getIntentos());

        if (entity.getPersona() != null) {
            Persona p = new Persona();
            p.setIdPersona(entity.getPersona().getIdPersona());
            p.setNombres(entity.getPersona().getNombres());
            p.setApellidos(entity.getPersona().getApellidos());
            p.setIdentificacion(entity.getPersona().getIdentificacion());
            p.setFechaNacimiento(entity.getPersona().getFechaNacimiento());
            model.setPersona(p);
        }

        // Corrección del mapeo de Roles a UsuarioRol
        if (entity.getRoles() != null) {
            model.setUsuarioRoles(entity.getRoles().stream().map(r -> {
                UsuarioRol ur = new UsuarioRol();
                Rol rol = new Rol();
                rol.setIdRol(r.getIdRol());
                rol.setRolName(r.getRolName());
                ur.setRol(rol);
                return ur;
            }).collect(Collectors.toList()));
        }
        return model;
    }

    private UsuarioEntity toEntity(Usuario model) {
        if (model == null) return null;
        UsuarioEntity entity = new UsuarioEntity();
        entity.setIdUsuario(model.getIdUsuario());
        entity.setUserName(model.getUserName());
        entity.setPassword(model.getPassword());
        entity.setMail(model.getMail());
        entity.setSessionActive(model.getSessionActive());
        entity.setStatus(model.getStatus());
        entity.setIntentos(model.getIntentos());

        if (model.getPersona() != null) {
            PersonaEntity pe = new PersonaEntity();
            pe.setIdPersona(model.getPersona().getIdPersona());
            pe.setNombres(model.getPersona().getNombres());
            pe.setApellidos(model.getPersona().getApellidos());
            pe.setIdentificacion(model.getPersona().getIdentificacion());
            pe.setFechaNacimiento(model.getPersona().getFechaNacimiento());
            entity.setPersona(pe);
        }
        
        // El mapeo de roles de model a entity suele requerir buscar las entidades en la DB
        // Para simplificar el save, si el model tiene roles, podrías mapearlos aquí.
        return entity;
    }
}