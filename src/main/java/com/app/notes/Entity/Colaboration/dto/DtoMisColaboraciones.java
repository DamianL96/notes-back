package com.app.notes.Entity.Colaboration.dto;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.Rol;

public record DtoMisColaboraciones(
        Long idNota,
        Rol rol
){
    public DtoMisColaboraciones(Colaboracion colab){
        this(
                colab.getNota().getId(),
                colab.getRol()
        );
    }
}
