package com.app.notes.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoLoginUsuario(
        @NotBlank(message = "El email no puede estar vacio")
        @Email(message = "Ingresa un Email válido")
        String email,

        @NotBlank(message ="El password no puede estar vacío")
        String password
) {
}
