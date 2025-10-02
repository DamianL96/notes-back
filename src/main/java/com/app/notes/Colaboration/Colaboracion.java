package com.app.notes.Colaboration;

import com.app.notes.Note.Nota;
import com.app.notes.User.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_id")
    private Nota nota;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

}
