package com.app.notes.Colaboration.dto;

import com.app.notes.Colaboration.Colaboracion;
import com.app.notes.Colaboration.Rol;

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
