package com.ProyectoSpring.Instituto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LegajoDtoRequest {
    @NotBlank(message = "Por favor, ingrese el número del legajo.")
    private String numero;
    @NotNull(message = "Por favor, ingrese el id del alumno.")
    @Positive(message = "El id del alumno debe ser un número positivo.")
    private Long idAlumno;
}
