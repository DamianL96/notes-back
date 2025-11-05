package com.app.notes.Entity.Colaboration.dto;

import com.app.notes.Entity.Colaboration.Rol;

public record DtoAgregarColaborador(
        Long id_usuario,
        Rol rol
) {
}
