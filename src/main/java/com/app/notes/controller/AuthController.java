package com.app.notes.controller;

import com.app.notes.dto.usuario.DtoRegistroUsuario;
import com.app.notes.model.Usuario;
import com.app.notes.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping("/register")
    public void registrar(@RequestBody @Valid DtoRegistroUsuario datos){
        var nuevoUsuario = new Usuario(datos);
        usuarioRepository.save(nuevoUsuario);
    }

    @Transactional
    @PostMapping("/login")
    public void login(@RequestBody @Valid DtoLoginUsuario datos){


    }

}
