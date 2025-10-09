package com.app.notes.Note;

import com.app.notes.Colaboration.ColaboracionService;
import com.app.notes.Note.dto.DtoCrearNota;
import com.app.notes.Note.dto.DtoDetalleNota;
import com.app.notes.Note.dto.DtoModificarNota;
import com.app.notes.User.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final ColaboracionService colaboracionService;

    public NotaService( NotaRepository notaRepo, ColaboracionService colaboracionServ){
        this.notaRepository = notaRepo;
        this.colaboracionService = colaboracionServ;
    }

    public DtoDetalleNota crearNota(Usuario usuario){
        var nota = new Nota();//crea la nota vacia
        var notaGuardada = notaRepository.save(nota);//guarda la nota y asigna un id
        colaboracionService.crearColaboracionPropietario(usuario,notaGuardada);//crea la colaboracion
        return new DtoDetalleNota(nota);
    }

    public DtoDetalleNota mostrarDetalleNota(Long id){
        var nota = notaRepository.getReferenceById(id);
        return new DtoDetalleNota(nota);
    }

    @Transactional
    public DtoDetalleNota modificarNota(DtoModificarNota datos){

        //verificar los permisos del usuario
        var nota = notaRepository.getReferenceById(datos.id());
        nota.actiualizarDatos(datos);
        return new DtoDetalleNota(nota);
    }

    @Transactional
    public void eliminarNota(Long id){
        notaRepository.deleteById(id);
    }
}
