package com.app.notes.Auth;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoLoginUsuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.exceptions.EmailAlreadyExistsException;
import com.app.notes.infrastructure.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private AuthenticationManager manager;
    @Mock
    private TokenService tokenService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private  AuthService authService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuarioNuevo(){
        DtoRegistroUsuario dto = new DtoRegistroUsuario("nuevo@test","nuevo test","123345");

        when(usuarioRepository.findByEmail(dto.email())).thenReturn(null);
        when(passwordEncoder.encode(dto.password())).thenReturn("Hashed");

        String result = authService.registrarUsuario(dto);

        assertEquals("El usuario se creó correctamente",result);

        verify(usuarioRepository).save(any());
    }

    @Test
    void testRegistrarUsuarioDuplicado(){
        DtoRegistroUsuario dto = new DtoRegistroUsuario("existente@test.com","existente test","123456");
        when(usuarioRepository.findByEmail(dto.email())).thenReturn(new Usuario());

        assertThrows(EmailAlreadyExistsException.class, ()->authService.registrarUsuario(dto));
        verify(usuarioRepository, never()).save(any());
    }


    @Test
    void testLoginExitoso(){
        DtoLoginUsuario dto = new DtoLoginUsuario("login@test.com","123456");
        Usuario usuario = new Usuario("login@test.com","nombre login","hashed");
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());

        when(manager.authenticate(authToken))
                .thenReturn(new UsernamePasswordAuthenticationToken(usuario, null));

        when(tokenService.generarToken(usuario))
                .thenReturn("jwt-token");

        var token = authService.login(dto);
        assertEquals("jwt-token",token.tokenJWT());
    }

}
