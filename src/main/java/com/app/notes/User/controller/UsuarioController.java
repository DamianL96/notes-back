package com.app.notes.User.controller;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoDetalleUsuario;
import com.app.notes.User.dto.DtoModificarNombreUsuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.User.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
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
