package com.app.notes.Note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notas")
public class NotaController {

    private final NotaRepository notaRepository;

    public NotaController( NotaRepository notaRepo){
        this.notaRepository = notaRepo;
    }

    @PostMapping
    public ResponseEntity crear(@RequestBody @Valid DtoCrearNota datos){
        var nota = new Nota(datos);
        notaRepository.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nota);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarDetalle(@PathVariable Long id){
        var nota = notaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoDetalleNota(nota));
    }
}
