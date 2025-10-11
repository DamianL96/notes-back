package com.app.notes.User.controller;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoDetalleUsuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {


    @GetMapping
    public DtoDetalleUsuario mostrarDetalleUsuario(@AuthenticationPrincipal Usuario usuario){
        return new DtoDetalleUsuario(usuario);
    }

}
