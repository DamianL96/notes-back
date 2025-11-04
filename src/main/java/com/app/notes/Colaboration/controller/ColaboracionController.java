package com.app.notes.Colaboration.controller;

import com.app.notes.Colaboration.ColaboracionService;
import com.app.notes.Colaboration.dto.DtoMisColaboraciones;
import com.app.notes.User.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/mias")
    public ResponseEntity<Page<DtoMisColaboraciones>> listarMisColaboraciones(
            @AuthenticationPrincipal Usuario usuario,
            @PageableDefault(size = 4, sort = {"id"})Pageable paginacion
    ){
         //colaboracionService.listarColaboraciones(usuario.getId());
        var colaboraciones = colaboracionService.listarColaboraciones(usuario.getId(),paginacion);
        return ResponseEntity.ok(colaboraciones);
    }
}
