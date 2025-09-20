package com.app.notes.User.controller;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DtoRegistroUsuario datos){

        var nuevoUsuario = new Usuario(datos);
        usuarioRepository.save(nuevoUsuario);
    }
}
