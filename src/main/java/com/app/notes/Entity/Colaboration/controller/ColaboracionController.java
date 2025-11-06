package com.app.notes.Entity.Colaboration.controller;

import com.app.notes.Entity.Colaboration.ColaboracionService;
import com.app.notes.Entity.Colaboration.dto.DtoColaboracion;
import com.app.notes.Entity.User.Usuario;
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
    public ResponseEntity<Page<DtoColaboracion>> listarMisColaboraciones(
            @AuthenticationPrincipal Usuario usuario,
            @PageableDefault(size = 4, sort = {"id"})Pageable paginacion
    ){
         //colaboracionService.listarColaboraciones(usuario.getId());
        var colaboraciones = colaboracionService.listarMisColaboraciones(usuario.getId(),paginacion);
        return ResponseEntity.ok(colaboraciones);
    }
}
