package com.app.notes.Colaboration;

import com.app.notes.Note.Nota;
import com.app.notes.User.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ColaboracionService {

    private final ColaboracionRepository colaboracionRepository;

    public ColaboracionService(ColaboracionRepository colaboracionRepo){
        this.colaboracionRepository = colaboracionRepo;
    }

    public void crearColaboracionPropietario(Usuario usuario, Nota nota){
        var colaboracion = new Colaboracion(usuario, nota);
        colaboracionRepository.save(colaboracion);
    }

}
