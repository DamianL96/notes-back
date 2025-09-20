package com.app.notes.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoRegistroUsuario(
        @NotBlank @Email String email,
        @NotBlank String nombre,
        @NotBlank String password
){}
