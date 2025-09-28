package com.app.notes.Note;

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


}
