package com.app.notes.Validations.UserValidations;

import com.app.notes.Entity.User.Usuario;
import com.app.notes.Entity.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor

@Component
public class ValidarExistenciaDeUsuario {

    private final UsuarioRepository usuarioRepo;

    public Usuario obtenerUsuarioSiExiste(Long idUsuario){
        return usuarioRepo.findById(idUsuario)
                .orElseThrow(()->new UserNotFoundException("El usuario con el id "+idUsuario+" no existe"));
    }

    public void validarUsuarioSiExiste(Long idUsuario){
        if(!usuarioRepo.existsById(idUsuario)){
            throw new UserNotFoundException("El usuario con el id "+idUsuario+" no existe");
        }
    }
}
