package com.app.notes.Entity.Note;

import com.app.notes.Entity.Colaboration.Colaboracion;
import com.app.notes.Entity.Colaboration.ColaboracionService;
import com.app.notes.Entity.Colaboration.Rol;
import com.app.notes.Entity.Note.dto.DtoDetalleNota;
import com.app.notes.Entity.Note.dto.DtoModificarNota;
import com.app.notes.Entity.User.Usuario;
import com.app.notes.Entity.User.repository.UsuarioRepository;
import com.app.notes.Validations.NoteValidations.ValidarExistenciaDeNota;
import com.app.notes.infrastructure.exceptions.NotaNotFoundException;
import com.app.notes.infrastructure.exceptions.WrongRolException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final ColaboracionService colaboracionService;
    private final UsuarioRepository usuarioRepository;

    private ValidarExistenciaDeNota validarExistenciaDeNota;


    public DtoDetalleNota crearNota(Usuario usuario){
        var nota = new Nota();//crea la nota vacia
        var notaGuardada = notaRepository.save(nota);//guarda la nota y asigna un id
        colaboracionService.crearColaboracionPropietario(usuario,notaGuardada);//crea la colaboracion
        return new DtoDetalleNota(nota);
    }

    public DtoDetalleNota mostrarDetalleNota(Long notaId, Long usuarioId){

        //validar que la nota exista
        var nota = validarExistenciaDeNota.obtenerNotaSiExiste(notaId);

        //verificar que el usuario tenga acceso
        colaboracionService.verificarColaboracionUsuarioNota(notaId, usuarioId);

        return new DtoDetalleNota(nota);
    }

    @Transactional
    public DtoDetalleNota modificarNota(DtoModificarNota datos, Usuario usuario){
        //verificar la existencia de la nota
        var nota = validarExistenciaDeNota.obtenerNotaSiExiste(datos.id());

        //verificar la colaboracion
        var colaboracion = colaboracionService.verificarColaboracionUsuarioNota(datos.id(), usuario.getId());

        //verificar los permisos
        if(colaboracion.getRol() == Rol.LECTOR){
            throw new WrongRolException("No tienes permiso para editar esta nota");
        }

        //var nota = notaRepository.getReferenceById(datos.id());
        nota.actiualizarDatos(datos);
        return new DtoDetalleNota(nota);
    }

    @Transactional
    public void eliminarNota(Long idNota, Usuario usuario){
        //verificar la existencia de la nota
        var nota = validarExistenciaDeNota.obtenerNotaSiExiste(idNota);

        //verificar la colaboracion
        var colaboracion = colaboracionService.verificarColaboracionUsuarioNota(idNota, usuario.getId());

        //verificar los permisos
        if(colaboracion.getRol() != Rol.PROPIETARIO){
            throw new WrongRolException("No tienes permiso para borrar esta nota");
        }

        //buscar todos los colaboradores de una nota especifica
        List<Colaboracion> colaboradores = colaboracionService.listarColaboradores(idNota);

        //remover todos los colaboradores de una nota especifica
        colaboradores.forEach(colaborador-> colaboracionService.removerColaborador(colaborador.getId()));

        //despues de remover todas las colaboraciones se elimina la nota
        notaRepository.deleteById(idNota);
    }
}
