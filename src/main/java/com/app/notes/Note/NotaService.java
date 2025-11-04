package com.app.notes.Note;

import com.app.notes.Colaboration.Colaboracion;
import com.app.notes.Colaboration.ColaboracionService;
import com.app.notes.Colaboration.Rol;
import com.app.notes.Note.dto.DtoDetalleNota;
import com.app.notes.Note.dto.DtoModificarNota;
import com.app.notes.User.Usuario;
import com.app.notes.User.repository.UsuarioRepository;
import com.app.notes.infrastructure.exceptions.NotaNotFoundException;
import com.app.notes.infrastructure.exceptions.UserNotFoundException;
import com.app.notes.infrastructure.exceptions.WrongRolException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final ColaboracionService colaboracionService;
    private final UsuarioRepository usuarioRepository;

    public NotaService( NotaRepository notaRepo, ColaboracionService colaboracionServ, UsuarioRepository usuarioRepo){
        this.notaRepository = notaRepo;
        this.colaboracionService = colaboracionServ;
        this.usuarioRepository = usuarioRepo;
    }

    public DtoDetalleNota crearNota(Usuario usuario){
        var nota = new Nota();//crea la nota vacia
        var notaGuardada = notaRepository.save(nota);//guarda la nota y asigna un id
        colaboracionService.crearColaboracionPropietario(usuario,notaGuardada);//crea la colaboracion
        return new DtoDetalleNota(nota);
    }

    public DtoDetalleNota mostrarDetalleNota(Long notaId, Long usuarioId){

        //validar que la nota exista
        var nota = notaRepository.findById(notaId)
                .orElseThrow(()-> new NotaNotFoundException("La nota con el id"+notaId+"no existe"));

        //verificar que el usuario tenga acceso
        colaboracionService.verificarColaboracionUsuarioNota(notaId, usuarioId);

        return new DtoDetalleNota(nota);
    }

    @Transactional
    public DtoDetalleNota modificarNota(DtoModificarNota datos, Usuario usuario){
        //verificar la existencia de la nota
        var nota = notaRepository.findById(datos.id())
                .orElseThrow(()-> new NotaNotFoundException("La nota con el id "+datos.id()+" no existe"));

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
        var nota = notaRepository.findById(idNota)
                .orElseThrow(()-> new NotaNotFoundException("La nota con el id "+idNota+" no existe"));

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
