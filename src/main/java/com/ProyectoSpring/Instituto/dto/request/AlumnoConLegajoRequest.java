package com.ProyectoSpring.Instituto.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlumnoConLegajoRequest {

    @NotBlank(message = "Por favor, ingrese el nombre del alumno.")
    private String nombre;

    @NotBlank(message = "Por favor, ingrese el apellido del alumno.")
    private String apellido;

    private Long idUsuario;

    @Valid
    @NotNull(message = "Por favor, ingrese los datos del legajo.")
    private LegajoAltaRequest legajo;
}
