package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.entidad.Legajo;

import java.util.List;

public interface ILegajoServicio {

    Legajo guardarLegajoParametro(Legajo legajo, Long idAlumno);

    Legajo guardarLegajoCuerpo(Legajo legajo);

    Legajo buscarPorId(Long id);

    List<Legajo> listarTodos();

    void eliminarLegajo(Long id);
}
