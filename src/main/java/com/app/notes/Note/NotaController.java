package com.app.notes.Note;

import com.app.notes.Note.dto.DtoModificarNota;
import com.app.notes.User.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {


    private final NotaService notaService;

    public NotaController( NotaService notaServ){
        this.notaService = notaServ;
    }

    @PostMapping
    public ResponseEntity crear(@AuthenticationPrincipal Usuario usuario){
        var detalleDeNota = notaService.crearNota(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleDeNota);
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarDetalleNota(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        var detalleDeNota = notaService.mostrarDetalleNota(id, usuario.getId());
        return ResponseEntity.ok(detalleDeNota);
    }

    @PutMapping
    public ResponseEntity modificarNota(@RequestBody @Valid DtoModificarNota datosNota, @AuthenticationPrincipal Usuario usuario){
        var detalleDeNota = notaService.modificarNota(datosNota, usuario);
        return ResponseEntity.ok(detalleDeNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarNota(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        notaService.eliminarNota(id, usuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
