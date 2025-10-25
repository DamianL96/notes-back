package com.app.notes.Colaboration.controller;

import com.app.notes.Colaboration.ColaboracionService;
import com.app.notes.User.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/colaboracion")
@RestController
public class ColaboracionController {

    private final ColaboracionService colaboracionService;

    public ColaboracionController (ColaboracionService colaboracionServ){
        this.colaboracionService = colaboracionServ;
    }

    @GetMapping
    public void listarMisColaboraciones(@AuthenticationPrincipal Usuario usuario){
         colaboracionService.listarColaboraciones(usuario.getId());

    }
}
