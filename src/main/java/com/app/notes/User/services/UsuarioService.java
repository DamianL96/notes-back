package com.app.notes.User.services;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoDetalleUsuario;
import com.app.notes.User.dto.DtoModificarNombreUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.exceptions.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public DtoDetalleUsuario modificarNombreUsuario(Long id, DtoModificarNombreUsuario datos){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Usuario no encontrado"));
        usuario.setNombre(datos.nombre());
        usuario.setPassword(passwordEncoder.encode(datos.password()));
        return new DtoDetalleUsuario(usuario);
    }


}
