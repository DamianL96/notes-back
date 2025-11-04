package com.app.notes.Note.dto;

import jakarta.validation.constraints.NotNull;

public record DtoCrearNota(
        @NotNull(message = "Se requiere un user Id para crear una nota")
        Long id_usuario
){}
