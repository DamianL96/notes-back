package com.app.notes.Auth;

import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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



}
