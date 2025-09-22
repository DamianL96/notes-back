package com.app.notes.Auth;

import com.app.notes.User.Usuario;
import com.app.notes.User.dto.DtoRegistroUsuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        usuarioRepository.deleteAll();
    }

    @Test
    void testRegistroExitoso() throws Exception{
        DtoRegistroUsuario dto = new DtoRegistroUsuario("nuevo@test.com","nombre nuevo","123456");

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("El usuario se creó correctamente"));
    }

    @Test
    void testRegistroDuplicado() throws Exception{

        usuarioRepository.save(new com.app.notes.User.Usuario(new DtoRegistroUsuario("Existente@test","nombre existente", passwordEncoder.encode("123456"))));

        DtoRegistroUsuario dto = new DtoRegistroUsuario("Existente@test","nombre existente","123456");

        mockMvc.perform( post("/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ya existe un usuario con esa cuenta"));
    }



}
