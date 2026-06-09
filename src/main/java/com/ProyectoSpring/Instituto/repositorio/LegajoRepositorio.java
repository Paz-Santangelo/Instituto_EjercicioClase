package com.ProyectoSpring.Instituto.repositorio;

import com.ProyectoSpring.Instituto.entidad.Legajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegajoRepositorio extends JpaRepository<Legajo, Long> {
}
