package com.app.notes.Colaboration;

import com.app.notes.Colaboration.dto.DtoAgregarColaborador;
import com.app.notes.Note.Nota;
import com.app.notes.User.Usuario;
import com.app.notes.infrastructure.exceptions.ColaborationNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

@Service
public class ColaboracionService {

    private final ColaboracionRepository colaboracionRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public void crearColaboracionPropietario(Usuario usuario, Nota nota){
        var colaboracion = new Colaboracion(usuario, nota);
        colaboracionRepository.save(colaboracion);
    }

    //buscar colaboracion de un usuario y nota especificamente
    public Colaboracion verificarColaboracionUsuarioNota(Long id_nota, Long id_usuario){
        Optional<Colaboracion> colaboracion =
                colaboracionRepository.findByUsuarioIdAndNotaId(id_usuario, id_nota);

        if(colaboracion.isEmpty()){
            throw new ColaborationNotFoundException("No existe una colaboracion entre el usuario"+id_usuario+" y la nota "+id_nota);
        }else {
            return colaboracion.get();
        }
    }

    public void agregarColaborador(Long id_nota, DtoAgregarColaborador datos){
        Nota nota = entityManager.getReference(Nota.class, id_nota);
        Usuario usuario = entityManager.getReference(Usuario.class, datos.id_usuario());
        var colaboracion = new Colaboracion(usuario,nota,datos.rol());
        colaboracionRepository.save(colaboracion);
    }


    public void removerColaborador(Long id_colaboracion){
        colaboracionRepository.deleteById(id_colaboracion);
    }


    public List<Colaboracion> listarColaboraciones(Long id){
        List<Colaboracion> colaboraciones = colaboracionRepository.findByUsuarioId(id);
        return colaboraciones;
    }

    public List<Colaboracion> listarColaboradores(Long id){
        List<Colaboracion> colaboradores = colaboracionRepository.findByNotaId(id);
        return colaboradores;
    }
}
