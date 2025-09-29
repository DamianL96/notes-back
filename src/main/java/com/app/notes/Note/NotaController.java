package com.app.notes.Note;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity mostrarDetalleNota(@PathVariable Long id){
        var nota = notaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoDetalleNota(nota));
    }

    @Transactional
    @PutMapping
    public ResponseEntity modificarNota(@RequestBody @Valid DtoModificarNota datos){
        var nota = notaRepository.getReferenceById(datos.id());
        nota.actiualizarDatos(datos);
        return ResponseEntity.ok(new DtoDetalleNota(nota));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarNota(@PathVariable Long id){
        notaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
