package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.request.AlumnoConLegajoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;

import java.util.List;

public interface IAlumnoServicio {

    AlumnoDtoResponse guardarAlumnoDto(AlumnoDtoRequest alumnoDto);

    AlumnoDtoResponse guardarAlumnoLegajo(AlumnoConLegajoRequest dto);

    Alumno buscarPorId(Long id);

    AlumnoDtoResponse buscarPorIdDto(Long id);

    List<AlumnoDtoResponse> listarTodos();

    void eliminarAlumno(Long id);

    List<Alumno> buscarPorApellido(String apellido);
}
