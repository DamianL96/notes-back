package com.app.notes.Auth;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.exceptions.EmailAlreadyExistsException;
import com.app.notes.infrastructure.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            AuthenticationManager manager,
            TokenService tokenService,
            PasswordEncoder passwordEncoder
    ){
        this.usuarioRepository = usuarioRepository;
        this.manager = manager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }
    //al ser una transaccion en la base de datos, si ocurre un error se cancela la operacion completamente
    @Transactional
    public ResponseEntity registrarUsuario(DtoRegistroUsuario datos){

        if(usuarioRepository.findByEmail(datos.email()) !=null){
            throw new EmailAlreadyExistsException("Ya existe un usuario con esa cuenta"); //la captura el AuthExceptionHandler
        }

        var nuevoUsuario = new Usuario(datos);
        nuevoUsuario.setPassword(passwordEncoder.encode(datos.password()));
        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("El usuario se creó correctamente");
    }

}
