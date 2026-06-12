package com.ProyectoSpring.Instituto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String token;
    private String tokenExpirationTime;
    private String rol;
    private AlumnoDtoResponse alumno;
}
