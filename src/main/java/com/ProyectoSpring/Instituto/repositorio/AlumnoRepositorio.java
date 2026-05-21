package com.ProyectoSpring.Instituto.repositorio;

import com.ProyectoSpring.Instituto.entidad.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {
}
