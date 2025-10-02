package com.app.notes.Note;

import com.app.notes.Note.dto.DtoCrearNota;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService( NotaRepository notaRepo){
        this.notaRepository = notaRepo;
    }

    public void crearNota(DtoCrearNota datos){

    }

}
