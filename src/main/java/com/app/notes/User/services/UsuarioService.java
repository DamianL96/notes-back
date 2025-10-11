package com.app.notes.User.services;

import com.app.notes.User.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepo){
        this.usuarioRepository = usuarioRepo;
    }

    public void mostrarDetalleUsuario(Long id){

    }


}
