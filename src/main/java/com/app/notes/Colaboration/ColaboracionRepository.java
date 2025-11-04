package com.app.notes.Colaboration;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboracionRepository extends JpaRepository<Colaboracion,Long> {

    Optional<Colaboracion> findByUsuarioIdAndNotaId(@NotNull Long usuarioId, @NotNull Long notaId);

    Page<Colaboracion> findByUsuarioId(Long usuarioId, Pageable paginacion);

    List<Colaboracion> findByNotaId(@NotNull Long notaId);
}
