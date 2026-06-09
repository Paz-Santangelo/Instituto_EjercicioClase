package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;

import java.util.List;

public interface IAlumnoServicio {

    Alumno guardarAlumno(Alumno alumno);

    AlumnoDtoResponse guardarAlumnoDto(AlumnoDtoRequest alumnoDto);

    Alumno buscarPorId(Long id);

    AlumnoDtoResponse buscarPorIdDto(Long id);

    List<Alumno> listarTodos();

    void eliminarAlumno(Long id);

    List<Alumno> buscarPorApellido(String apellido);
}
