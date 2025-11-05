package com.app.notes.Auth;

import com.app.notes.Entity.User.dto.DtoDetalleUsuario;
import com.app.notes.Entity.User.dto.DtoLoginUsuario;
import com.app.notes.Entity.User.dto.DtoRegistroUsuario;
import com.app.notes.infrastructure.security.DTOTokenJWT;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DtoDetalleUsuario> registrar(@RequestBody @Valid DtoRegistroUsuario datos){
        DtoDetalleUsuario dto = authService.registrarUsuario(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<DTOTokenJWT> login(@RequestBody @Valid DtoLoginUsuario datos){
        DTOTokenJWT token = authService.login(datos);
        return ResponseEntity.ok(token);
    }

}
