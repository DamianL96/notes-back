package com.app.notes.Validations.NoteValidations;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.Rol;
import com.app.notes.infrastructure.exceptions.WrongRolException;
import org.springframework.stereotype.Component;


@Component
public class ValidarPermisos {


    public void puedeEditar(Colaboracion colaboracion){
        if(colaboracion.getRol() == Rol.LECTOR){
            throw new WrongRolException("No tienes permiso para editar esta nota");
        }
    }

    public void esPropietario(Colaboracion colaboracion){
        if(colaboracion.getRol() != Rol.PROPIETARIO){
            throw new WrongRolException("Debes ser propietario de la nota para realizar esa acción");
        }
    }
}
