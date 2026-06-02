package com.ProyectoSpring.Instituto.mapper;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;

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
}
