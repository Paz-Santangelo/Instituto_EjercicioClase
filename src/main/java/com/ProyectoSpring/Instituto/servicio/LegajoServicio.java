package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.entidad.Legajo;
import com.ProyectoSpring.Instituto.repositorio.AlumnoRepositorio;
import com.ProyectoSpring.Instituto.repositorio.LegajoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LegajoServicio implements ILegajoServicio {

    @Autowired
    private LegajoRepositorio legajoRepo;

    @Autowired
    private IAlumnoServicio alumnoServ;

    @Override
    public Legajo guardarLegajoParametro(Legajo legajo, Long idAlumno) {
        Alumno alumnoEncontrado = alumnoServ.buscarPorId(idAlumno);

        if (legajo.getNumero() == null || legajo.getNumero().trim().isEmpty()) {
            throw new IllegalArgumentException("El número del legajo no puede estar vacío");
        }

        legajo.setFechaAlta(LocalDate.now());
        legajo.setAlumno(alumnoEncontrado);
        return legajoRepo.save(legajo);
    }

    @Override
    public Legajo guardarLegajoCuerpo(Legajo legajo) {
        Alumno alumnoEncontrado = alumnoServ.buscarPorId(legajo.getAlumno().getId());

        if (legajo.getNumero() == null || legajo.getNumero().trim().isEmpty()) {
            throw new IllegalArgumentException("El número del legajo no puede estar vacío");
        }

        legajo.setFechaAlta(LocalDate.now());
        legajo.setAlumno(alumnoEncontrado);
        return legajoRepo.save(legajo);
    }

    @Override
    public Legajo buscarPorId(Long id) {
        return legajoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Legajo no encontrado con id: " + id));
    }

    @Override
    public List<Legajo> listarTodos() {
        return legajoRepo.findAll();
    }

    @Override
    public void eliminarLegajo(Long id) {
        Legajo legajo = buscarPorId(id);
        if (legajo != null) {
            legajoRepo.delete(legajo);
        }
    }
}
