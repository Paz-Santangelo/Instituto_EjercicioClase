package com.ProyectoSpring.Instituto.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LegajoAltaRequest {

    @NotBlank(message = "Por favor, ingrese el número del legajo.")
    private String numero;
}
