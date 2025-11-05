package com.app.notes.Entity.User.dto;

import com.app.notes.Entity.User.Usuario;

import java.time.LocalDateTime;

public record DtoDetalleUsuario(
        Long id,
        String email,
        String nombre,
        LocalDateTime fecha_creacion
) {
    public DtoDetalleUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getFecha_creacion()
        );
    }
}
