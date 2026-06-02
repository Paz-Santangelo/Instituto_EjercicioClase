package com.ProyectoSpring.Instituto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlumnoDtoResponse {
    private String nombre;
    private String apellido;
    private LegajoDtoResponse legajo;
}
