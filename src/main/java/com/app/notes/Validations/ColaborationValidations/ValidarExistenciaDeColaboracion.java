package com.app.notes.Validations.ColaborationValidations;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.ColaboracionRepository;
import com.app.notes.infrastructure.exceptions.ColaborationNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor

@Component
public class ValidarExistenciaDeColaboracion {

    private final ColaboracionRepository colaboracionRepo;

    public Colaboracion obtenerColaboracionSiExiste(Long idNota, Long idUsuario){
        Optional<Colaboracion> colaboracion =
                colaboracionRepo.findByUsuarioIdAndNotaId(idUsuario,idNota);

        if(colaboracion.isEmpty()){
            throw new ColaborationNotFoundException("No existe una colaboracion entre el usuario "+idUsuario+" y la nota "+idNota);
        }else {
            return colaboracion.get();
        }
    }

    public void validarColaboracionSiExiste(Long idNota, Long idUsuario){
        Optional<Colaboracion> colaboracion =
                colaboracionRepo.findByUsuarioIdAndNotaId(idUsuario,idNota);

        if(colaboracion.isEmpty()){
            throw new ColaborationNotFoundException("No existe una colaboracion entre el usuario "+idUsuario+" y la nota "+idNota);
        }
    }

}
