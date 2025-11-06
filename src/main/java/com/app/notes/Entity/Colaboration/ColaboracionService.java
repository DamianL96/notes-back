package com.app.notes.Entity.Colaboration;

import com.app.notes.Entity.Colaboration.dto.DtoAgregarColaborador;
import com.app.notes.Entity.Colaboration.dto.DtoColaborador;
import com.app.notes.Entity.Colaboration.dto.DtoColaboracion;
import com.app.notes.Entity.Note.Nota;
import com.app.notes.Entity.User.Usuario;
import com.app.notes.Validations.ColaborationValidations.ValidarDuplicadoDeColaboracion;
import com.app.notes.Validations.ColaborationValidations.ValidarExistenciaDeColaboracion;
import com.app.notes.Validations.NoteValidations.ValidarExistenciaDeNota;
import com.app.notes.Validations.NoteValidations.ValidarPermisos;
import com.app.notes.Validations.UserValidations.ValidarExistenciaDeUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class ColaboracionService {

    private final ColaboracionRepository colaboracionRepository;

    private ValidarExistenciaDeNota VExistenciaDeNota;
    private ValidarPermisos VPermisos;
    private ValidarExistenciaDeColaboracion VExistenciaDeColaboracion;
    private ValidarExistenciaDeUsuario VExistenciaDeUsuario;
    private ValidarDuplicadoDeColaboracion VDuplicadoDeColaboracion;


    @PersistenceContext
    private EntityManager entityManager;


    public void crearColaboracionPropietario(Usuario usuario, Nota nota){
        var colaboracion = new Colaboracion(usuario, nota);
        colaboracionRepository.save(colaboracion);
    }

    public void agregarColaborador(Long idNota, Usuario usuario, DtoAgregarColaborador colaborador){

        //validar existencia de la nota, permisos, colaborador, duplicado
        VExistenciaDeNota.validarNotaSiExiste(idNota);
        var usuarioColaboracion = VExistenciaDeColaboracion.obtenerColaboracionSiExiste(idNota, usuario.getId());
        VPermisos.esPropietario(usuarioColaboracion);
        VExistenciaDeUsuario.validarUsuarioSiExiste(colaborador.id_usuario());
        VDuplicadoDeColaboracion.buscarColaboracionSiExiste(colaborador.id_usuario(), idNota);

        //Obtiene la nota, el usuario, crea la colaboracion y la guarda
        Nota nota = entityManager.getReference(Nota.class, idNota);
        Usuario nuevoColaborador = entityManager.getReference(Usuario.class, colaborador.id_usuario());
        var colaboracion = new Colaboracion(nuevoColaborador,nota,colaborador.rol());
        colaboracionRepository.save(colaboracion);
    }


    public void removerColaborador(Long id_colaboracion){
        colaboracionRepository.deleteById(id_colaboracion);
    }


    public Page<DtoColaboracion> listarMisColaboraciones(Long id, Pageable paginacion){
        //List<Colaboracion> colaboraciones = colaboracionRepository.findByUsuarioId(id);
        Page<Colaboracion> page = colaboracionRepository.findByUsuarioId(id,paginacion);
        return page.map(DtoColaboracion::new);
    }

    public List<DtoColaboracion> listarColaboraciones(Long idNota, Usuario usuario){
        VExistenciaDeNota.validarNotaSiExiste(idNota);
        VExistenciaDeColaboracion.validarColaboracionSiExiste(idNota, usuario.getId());

        return colaboracionRepository.findByNotaId(idNota)
                .stream().map(DtoColaboracion::new).toList();
    }

    public List<DtoColaborador> listarColaboradores(Long idNota, Usuario usuario){
        VExistenciaDeNota.validarNotaSiExiste(idNota);
        VExistenciaDeColaboracion.validarColaboracionSiExiste(idNota, usuario.getId());

        return colaboracionRepository.findByNotaId(idNota)
                .stream().map(DtoColaborador::deColaboracion)
                .toList();
    }
}
