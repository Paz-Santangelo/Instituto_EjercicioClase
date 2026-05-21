package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.entidad.Alumno;

import java.util.List;

public interface IAlumnoServicio {

    Alumno guardarAlumno(Alumno alumno);

    Alumno buscarPorId(Long id);

    List<Alumno> listarTodos();

    void eliminarAlumno(Long id);
}
