package com.app.notes.Entity.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoLoginUsuario(
        @NotBlank(message = "El email no puede estar vacio")
        @Email(message = "Ingresa un Email válido")
        String email,

        @NotBlank(message ="El password no puede estar vacío")
        String password
) {
}
