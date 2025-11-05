package com.app.notes.Entity.User.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoModificarNombreUsuario(
        String nombre,

        @Size(min=8,max=24, message = "El password debe tener mas de 8 carácteres y menos de 24 carácteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "El password debe contener al menos una letra mayúscula y un número")
        String password

) {}
