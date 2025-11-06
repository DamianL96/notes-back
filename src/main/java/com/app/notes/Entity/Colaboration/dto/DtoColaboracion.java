package com.app.notes.Entity.Colaboration.dto;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.Rol;

public record DtoColaboracion(
        Long idColaboracion,
        Long idNota,
        Long idUsuario,
        Rol rol
) {

    public DtoColaboracion(Colaboracion colab){
        this(
                colab.getId(),
                colab.getNota().getId(),
                colab.getUsuario().getId(),
                colab.getRol()
        );
    }
}
