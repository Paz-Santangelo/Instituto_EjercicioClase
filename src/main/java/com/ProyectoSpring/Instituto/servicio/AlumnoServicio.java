package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.repositorio.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicio implements IAlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepo;

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        // Validar que el nombre no esté vacío
        if (alumno.getNombre() == null || alumno.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío");
        }

        // Validar que el apellido no esté vacío
        if (alumno.getApellido() == null || alumno.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del alumno no puede estar vacío");
        }

        // Si las validaciones pasan, se guarda el alumno
        return alumnoRepo.save(alumno);
    }

    @Override
    public Alumno buscarPorId(Long id) {
        Alumno alumno = alumnoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con i: " + id));
        return alumno;
    }

    @Override
    public List<Alumno> listarTodos() {
        return alumnoRepo.findAll();
    }

    @Override
    public void eliminarAlumno(Long id) {
        Alumno alumno = buscarPorId(id);
        if (alumno != null) {
            alumnoRepo.delete(alumno);
        }
    }
}
