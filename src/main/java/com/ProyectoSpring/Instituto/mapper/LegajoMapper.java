package com.ProyectoSpring.Instituto.mapper;

import com.ProyectoSpring.Instituto.dto.response.LegajoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Legajo;

public class LegajoMapper {

    public static LegajoDtoResponse toDto(Legajo legajo) {
        if (legajo == null) {
            return null;
        }

        LegajoDtoResponse dto = new LegajoDtoResponse();
        dto.setNumero(legajo.getNumero());
        dto.setFechaAlta(legajo.getFechaAlta());
        return dto;
    }

}
