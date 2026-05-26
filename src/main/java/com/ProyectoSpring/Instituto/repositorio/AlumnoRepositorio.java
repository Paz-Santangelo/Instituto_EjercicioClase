package com.ProyectoSpring.Instituto.repositorio;

import com.ProyectoSpring.Instituto.entidad.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {
    // Buscar alumnos por apellido exacto
    List<Alumno> findByApellido(String apellido);

    // Buscar alumnos cuyo nombre contenga una cadena (sin importar mayúsculas/minúsculas)
    List<Alumno> findByNombreContainingIgnoreCase(String nombre);

    // Buscar un alumno por su legajo (usando el atributo de la relación)
    Alumno findByLegajoNumero(String numeroLegajo);

    // Buscar alumnos por nombre de materia
    @Query("SELECT a FROM Alumno a JOIN a.materias m WHERE m.nombre LIKE %:nombreParcial%")
    List<Alumno> findByMateriaNombreContaining(@Param("nombreParcial") String nombreParcial);
}
