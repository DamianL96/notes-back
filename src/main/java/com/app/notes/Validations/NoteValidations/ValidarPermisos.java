package com.app.notes.Validations.NoteValidations;

import com.app.notes.Entity.Colaboration.Rol;
import com.app.notes.infrastructure.exceptions.WrongRolException;
import org.springframework.stereotype.Component;

@Component
public class ValidarPermisos {
    public void puedeEditar(Rol rol){
        if(rol == Rol.LECTOR){
            throw new WrongRolException("No tienes permiso para editar esta nota");
        }
    }

    public void esPropietario(Rol rol){
        if(rol != Rol.PROPIETARIO){
            throw new WrongRolException("No tienes permiso para eiminar esta nota");
        }
    }
}
