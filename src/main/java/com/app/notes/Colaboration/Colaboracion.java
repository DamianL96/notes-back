package com.app.notes.Colaboration;

import com.app.notes.Note.Nota;
import com.app.notes.User.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="colaboraciones")
@Entity(name="Colaboracion")

//lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Colaboracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nota")
    private Nota nota;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDateTime creado_en;

    public Colaboracion(Usuario usuario, Nota nota){
        this.usuario = usuario;
        this.nota = nota;
        this.rol = Rol.PROPIETARIO;
        this.creado_en = LocalDateTime.now();
    }

    public Colaboracion(Usuario usuario, Nota nota, Rol rol){
        this.usuario = usuario;
        this.nota = nota;
        this.rol = rol;
        this.creado_en = LocalDateTime.now();
    }

}
