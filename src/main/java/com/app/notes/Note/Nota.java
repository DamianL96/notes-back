package com.app.notes.Note;

import com.app.notes.Note.dto.DtoCrearNota;
import com.app.notes.Note.dto.DtoModificarNota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="notas")
@Entity(name="Nota")

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String cuerpo;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_modificacion;


    public Nota(DtoCrearNota datos){
        this.fecha_creacion = LocalDateTime.now();
    }

    public void actiualizarDatos(DtoModificarNota datos){
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if(datos.cuerpo() != null){
            this.cuerpo = datos.cuerpo();
        }
        this.fecha_modificacion = LocalDateTime.now();
    }


}
