package com.app.notes.Validations.ColaborationValidations;
import com.app.notes.Entity.Colaboration.ColaboracionRepository;
import com.app.notes.infrastructure.exceptions.DuplicateResourceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ValidarDuplicadoDeColaboracion {

    private final ColaboracionRepository colaboracionRepo;

    public void buscarColaboracionSiExiste(Long idUsuario, Long idNota){
        if(colaboracionRepo.existsByUsuarioIdAndNotaId(idUsuario,idNota)){
            throw new DuplicateResourceException("Ya existe una colaboracion entre el usuario "+idUsuario+" y la nota "+idNota);
        }
    }
}
