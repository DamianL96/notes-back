package com.app.notes.Colaboration;

import com.app.notes.Note.Nota;
import com.app.notes.User.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class ColaboracionService {

    private final ColaboracionRepository colaboracionRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public ColaboracionService(ColaboracionRepository colaboracionRepo){
        this.colaboracionRepository = colaboracionRepo;
    }

    public void crearColaboracionPropietario(Usuario usuario, Nota nota){
        var colaboracion = new Colaboracion(usuario, nota);
        colaboracionRepository.save(colaboracion);
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

}
