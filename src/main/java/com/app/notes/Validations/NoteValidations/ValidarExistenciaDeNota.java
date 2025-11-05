package com.app.notes.Validations.NoteValidations;

import com.app.notes.Entity.Note.Nota;
import com.app.notes.Entity.Note.NotaRepository;
import com.app.notes.infrastructure.exceptions.NotaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ValidarExistenciaDeNota {

    private NotaRepository notaRepository;

    public Nota obtenerNotaSiExiste(Long idNota){
        return notaRepository.findById(idNota)
                .orElseThrow(()-> new NotaNotFoundException("La nota con el id "+ idNota +" no existe"));
    }

    public void validarNotaSiExiste(Long idNota){
        if(!notaRepository.existsById(idNota)){
            throw new NotaNotFoundException("La nota con el id "+ idNota +" no existe");
        }
    }
}
