package com.app.notes.Auth;

import com.app.notes.User.dto.DtoLoginUsuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.Usuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.security.DTOTokenJWT;
import com.app.notes.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {



    @Transactional
    @PostMapping("/register")
    public ResponseEntity registrar(@RequestBody @Valid DtoRegistroUsuario datos){



        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DtoLoginUsuario datos){

        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(),datos.password());
            var autenticacion = manager.authenticate(authenticationToken);

            var token = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

            return ResponseEntity.ok(new DTOTokenJWT(token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Credenciales invalidas");
        }




    }

}
