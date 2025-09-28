package com.app.notes.Note;

import jakarta.validation.constraints.NotBlank;

public record DtoCrearNota(
        @NotBlank String titulo
){}
