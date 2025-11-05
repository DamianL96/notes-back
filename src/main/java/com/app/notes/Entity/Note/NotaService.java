package com.app.notes.Entity.Note;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.ColaboracionService;
import com.app.notes.Entity.Note.dto.DtoDetalleNota;
import com.app.notes.Entity.Note.dto.DtoModificarNota;
import com.app.notes.Entity.User.Usuario;
import com.app.notes.Validations.ColaborationValidations.ValidarExistenciaDeColaboracion;
import com.app.notes.Validations.NoteValidations.ValidarExistenciaDeNota;
import com.app.notes.Validations.NoteValidations.ValidarPermisos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final ColaboracionService colaboracionService;

    private ValidarExistenciaDeNota VExistenciaDeNota;
    private ValidarPermisos VPermisos;
    private ValidarExistenciaDeColaboracion VExistenciaDeColaboracion;


    public DtoDetalleNota crearNota(Usuario usuario){
        var nota = new Nota();//crea la nota vacia
        var notaGuardada = notaRepository.save(nota);//guarda la nota y asigna un id
        colaboracionService.crearColaboracionPropietario(usuario,notaGuardada);//crea la colaboracion
        return new DtoDetalleNota(nota);
    }

    public DtoDetalleNota mostrarDetalleNota(Long notaId, Long usuarioId){

        //validar que la nota exista
        var nota = VExistenciaDeNota.obtenerNotaSiExiste(notaId);

        //verificar que el usuario tenga acceso
        VExistenciaDeColaboracion.validarColaboracionSiExiste(notaId,usuarioId);

        return new DtoDetalleNota(nota);
    }

    @Transactional
    public DtoDetalleNota modificarNota(DtoModificarNota datos, Usuario usuario){
        //verificar la existencia de la nota
        var nota = VExistenciaDeNota.obtenerNotaSiExiste(datos.id());

        //verificar la colaboracion
        var colaboracion = VExistenciaDeColaboracion.obtenerColaboracionSiExiste(datos.id(), usuario.getId());

        //verificar los permisos
        VPermisos.puedeEditar(colaboracion);

        nota.actiualizarDatos(datos);
        return new DtoDetalleNota(nota);
    }

    @Transactional
    public void eliminarNota(Long idNota, Usuario usuario){

        //validar la existencia de la nota, colaboracion y permisos
        VExistenciaDeNota.validarNotaSiExiste(idNota);
        var colaboracion = VExistenciaDeColaboracion.obtenerColaboracionSiExiste(idNota, usuario.getId());
        VPermisos.esPropietario(colaboracion);

        //buscar todos los colaboradores de una nota especifica
        List<Colaboracion> colaboradores = colaboracionService.listarColaboradores(idNota);

        //remover todos los colaboradores de una nota especifica
        colaboradores.forEach(colaborador-> colaboracionService.removerColaborador(colaborador.getId()));

        //despues de remover todas las colaboraciones se elimina la nota
        notaRepository.deleteById(idNota);
    }
}
