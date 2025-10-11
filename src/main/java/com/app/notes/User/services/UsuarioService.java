package com.app.notes.User.services;

import com.app.notes.User.Usuario;
import com.app.notes.User.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepo){
        this.usuarioRepository = usuarioRepo;
    }

    @Transactional
    public void modificarNombreUsuario(Long id, String nombre){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.setNombre(nombre);
    }


}
