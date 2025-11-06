package com.app.notes.Entity.Colaboration.dto;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.Rol;
import com.app.notes.Entity.User.Usuario;

public record DtoColaborador(
        //id, nombre, rol
        Long id,
        String email,
        String nombre,
        Rol rol
) {
    public static DtoColaborador deColaboracion(Colaboracion colaboracion){
        Usuario usuario = colaboracion.getUsuario();
        return new DtoColaborador(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNombre(),
                colaboracion.getRol()
        );
    }
}
