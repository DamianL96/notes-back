package com.app.notes.Note;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/nota")
@RestController
public class NoteController {

    //inyectar repositorio

    @PostMapping
    public void crearNota(){

    }
}
