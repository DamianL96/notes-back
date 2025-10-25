package com.app.notes.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoRegistroUsuario(
        @NotBlank(message = "El email no puede estar vacio")
        @Email(message = "Ingresa un Email válido")
        String email,

        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message ="El password no puede estar vacío")
        @Size(min=8,max=24, message = "El password debe tener mas de 8 carácteres y menos de 24 carácteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "El password debe contener al menos una letra mayúscula y un número")
        String password
){}
