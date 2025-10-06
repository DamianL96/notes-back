package com.app.notes.Auth;

import com.app.notes.User.dto.DtoLoginUsuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.Usuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.security.DTOTokenJWT;
import com.app.notes.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authServ){
        this.authService = authServ;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody @Valid DtoRegistroUsuario datos){
        String message= authService.registrarUsuario(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<DTOTokenJWT> login(@RequestBody @Valid DtoLoginUsuario datos){
        DTOTokenJWT token = authService.login(datos);
        return ResponseEntity.ok(token);
    }

}
