package com.app.notes.Note.dto;

import jakarta.validation.constraints.NotNull;

public record DtoModificarNota(
        @NotNull Long id,
        String titulo,
        String cuerpo
) {}
