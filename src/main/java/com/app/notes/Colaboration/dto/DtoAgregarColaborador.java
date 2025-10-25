package com.app.notes.Colaboration.dto;

import com.app.notes.Colaboration.Rol;

public record DtoAgregarColaborador(
        Long id_usuario,
        Rol rol
) {
}
