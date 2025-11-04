package com.app.notes.Colaboration;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboracionRepository extends JpaRepository<Colaboracion,Long> {

    Optional<Colaboracion> findByUsuarioIdAndNotaId(@NotNull Long usuarioId, @NotNull Long notaId);

    List<Colaboracion> findByUsuarioId(@NotNull Long usuarioId);

    List<Colaboracion> findByNotaId(@NotNull Long notaId);
}
