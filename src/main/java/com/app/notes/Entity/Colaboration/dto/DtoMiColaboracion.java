package com.app.notes.Entity.Colaboration.dto;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.Rol;

public record DtoMiColaboracion(
        Long idNota,
        Rol rol
){
    public DtoMiColaboracion(Colaboracion colab){
        this(
                colab.getNota().getId(),
                colab.getRol()
        );
    }
}
