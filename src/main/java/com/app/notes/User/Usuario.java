package com.app.notes.User;


import com.app.notes.User.dto.DtoRegistroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="usuarios")
@Entity(name="Usuario")

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String nombre;
    private String password;
    private LocalDateTime fecha_creacion;


    public Usuario(DtoRegistroUsuario datos){
        this.id = null;
        this.email = datos.email();
        this.nombre = datos.nombre();
        this.password = datos.password();
        this.fecha_creacion = LocalDateTime.now();
    }

}
