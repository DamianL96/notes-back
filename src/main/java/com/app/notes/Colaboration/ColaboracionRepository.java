package com.app.notes.Colaboration;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColaboracionRepository extends JpaRepository<Colaboracion,Long> {

    List<Colaboracion> findByUsuarioId(@NotNull Long usuarioId);
}
