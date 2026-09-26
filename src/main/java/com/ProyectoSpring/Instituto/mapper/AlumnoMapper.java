package com.ProyectoSpring.Instituto.mapper;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.request.AlumnoConLegajoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.entidad.Legajo;

public class AlumnoMapper {

    public static AlumnoDtoResponse toDto(Alumno alumno) {
        if (alumno == null) {
            return null;
        }

        AlumnoDtoResponse dto = new AlumnoDtoResponse();
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setLegajo(LegajoMapper.toDto(alumno.getLegajo()));
        return dto;
    }

    public static Alumno toEntity(AlumnoDtoRequest dto) {
        if (dto == null) {
            return null;
        }

        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());
        return alumno;
    }

    /**
     * Construye el agregado Alumno-Legajo y deja ambas puntas de la relación
     * sincronizadas. La fecha de alta corresponde a una regla de negocio y se
     * asigna desde el servicio.
     */
    public static Alumno toEntityConLegajo(AlumnoConLegajoRequest dto) {
        if (dto == null) {
            return null;
        }

        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());

        Legajo legajo = new Legajo();
        legajo.setNumero(dto.getLegajo().getNumero());
        legajo.setAlumno(alumno);
        alumno.setLegajo(legajo);

        return alumno;
    }
}
