package com.app.notes.Note.controller;

import com.app.notes.Colaboration.ColaboracionService;
import com.app.notes.Colaboration.DtoAgregarColaborador;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notas/{id}/colaboraciones")
public class NotaColaboracionController {

    private final ColaboracionService colaboracionService;

    public NotaColaboracionController(ColaboracionService colaboracionServ){
        this.colaboracionService = colaboracionServ;
    }

    @PostMapping
    public ResponseEntity crear(@PathVariable Long id, @RequestBody @Valid DtoAgregarColaborador datos){

        colaboracionService.agregarColaborador(id, datos);
        return ResponseEntity.ok().body("colaborador agregado con exito");
    }

    @DeleteMapping("/{id_colaboracion}")
    public ResponseEntity removerColaborador(@PathVariable Long id_colaboracion){
        colaboracionService.removerColaborador(id_colaboracion);
        return ResponseEntity.ok().body("Colaborador removido con exito");
    }

}
