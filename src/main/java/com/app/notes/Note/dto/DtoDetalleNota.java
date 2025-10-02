package com.app.notes.Note.dto;

import com.app.notes.Note.Nota;

import java.time.LocalDateTime;

public record DtoDetalleNota(
        String titulo,
        String cuerpo,
        LocalDateTime fecha_modificacion
) {
    public DtoDetalleNota(Nota nota){
        this(
                nota.getTitulo(),
                nota.getCuerpo(),
                nota.getFecha_modificacion()
        );
    }
}
