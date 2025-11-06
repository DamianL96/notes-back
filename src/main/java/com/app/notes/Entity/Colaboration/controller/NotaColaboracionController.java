package com.app.notes.Entity.Colaboration.controller;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.ColaboracionService;
import com.app.notes.Entity.Colaboration.dto.DtoAgregarColaborador;
import com.app.notes.Entity.Colaboration.dto.DtoColaborador;
import com.app.notes.Entity.User.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas/{id}/colaboraciones")
public class NotaColaboracionController {

    private final ColaboracionService colaboracionService;

    public NotaColaboracionController(ColaboracionService colaboracionServ){
        this.colaboracionService = colaboracionServ;
    }

    @PostMapping
    public ResponseEntity agregarColaborador(
            @PathVariable Long id,
            @RequestBody @Valid DtoAgregarColaborador datos,
            @AuthenticationPrincipal Usuario usuario){

        colaboracionService.agregarColaborador(id, usuario, datos);
        return ResponseEntity.ok().body("colaborador agregado con exito");
    }

    @DeleteMapping("/{id_colaboracion}")
    public ResponseEntity removerColaborador(@PathVariable Long id_colaboracion){
        colaboracionService.removerColaborador(id_colaboracion);
        return ResponseEntity.ok().body("Colaborador removido con exito");
    }

    @GetMapping
    public ResponseEntity obtenerColaboradores(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        List<DtoColaborador> colaboradores= colaboracionService.listarColaboradores(id,usuario);
        return ResponseEntity.ok().body(colaboradores);
    }

}
