package com.app.notes.Entity.User.controller;

import com.app.notes.Entity.User.Usuario;
import com.app.notes.Entity.User.dto.DtoDetalleUsuario;
import com.app.notes.Entity.User.dto.DtoModificarNombreUsuario;
import com.app.notes.Entity.User.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/usuario")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioSrev){
        this.usuarioService = usuarioSrev;
    }

    @GetMapping("/me")
    public ResponseEntity<DtoDetalleUsuario> mostrarDetalleUsuario(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(new DtoDetalleUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<DtoDetalleUsuario> modificarNombreUsuario(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DtoModificarNombreUsuario datos){
        DtoDetalleUsuario dto = usuarioService.modificarNombreUsuario(usuario.getId(), datos);
        return ResponseEntity.ok().body(dto);
    }

}
