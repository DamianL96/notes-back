package com.app.notes.Note;

import com.app.notes.Note.dto.DtoCrearNota;
import com.app.notes.Note.dto.DtoDetalleNota;
import com.app.notes.Note.dto.DtoModificarNota;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService( NotaRepository notaRepo){
        this.notaRepository = notaRepo;
    }

    public DtoDetalleNota crearNota(DtoCrearNota datos){
        //verificar que el usuario esté autenticado

        var nota = new Nota(datos);
        notaRepository.save(nota);
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
