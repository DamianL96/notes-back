package com.app.notes.Note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notas")
public class NotaController {

    @PostMapping
    public ResponseEntity crear(@RequestBody @Valid DtoCrearNota datos){
        System.out.println(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(datos.titulo());
    }
}
