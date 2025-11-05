package com.app.notes.Entity.Colaboration;

import com.app.notes.Entity.Colaboration.dto.DtoAgregarColaborador;
import com.app.notes.Entity.Colaboration.dto.DtoMisColaboraciones;
import com.app.notes.Entity.Note.Nota;
import com.app.notes.Entity.User.Usuario;
import com.app.notes.Validations.NoteValidations.ValidarExistenciaDeNota;
import com.app.notes.Validations.NoteValidations.ValidarPermisos;
import com.app.notes.infrastructure.exceptions.ColaborationNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class ColaboracionService {

    private final ColaboracionRepository colaboracionRepository;

    private ValidarExistenciaDeNota VExistenciaDeNota;
    private ValidarPermisos VPermisos;

    @PersistenceContext
    private EntityManager entityManager;


    public void crearColaboracionPropietario(Usuario usuario, Nota nota){
        var colaboracion = new Colaboracion(usuario, nota);
        colaboracionRepository.save(colaboracion);
    }

    public void agregarColaborador(Long id_nota, DtoAgregarColaborador datos){


        //validar existencia de la nota
        //verificar propietario
        //verificar existencia de usuario colaborador
        //no duplicar colaboraciones
        Nota nota = entityManager.getReference(Nota.class, id_nota);
        Usuario usuario = entityManager.getReference(Usuario.class, datos.id_usuario());
        var colaboracion = new Colaboracion(usuario,nota,datos.rol());
        colaboracionRepository.save(colaboracion);
    }


    public void removerColaborador(Long id_colaboracion){
        colaboracionRepository.deleteById(id_colaboracion);
    }


    public Page<DtoMisColaboraciones> listarColaboraciones(Long id, Pageable paginacion){
        //List<Colaboracion> colaboraciones = colaboracionRepository.findByUsuarioId(id);
        Page<Colaboracion> page = colaboracionRepository.findByUsuarioId(id,paginacion);
        return page.map(DtoMisColaboraciones::new);
    }

    public List<Colaboracion> listarColaboradores(Long id){
        List<Colaboracion> colaboradores = colaboracionRepository.findByNotaId(id);
        return colaboradores;
    }
}
