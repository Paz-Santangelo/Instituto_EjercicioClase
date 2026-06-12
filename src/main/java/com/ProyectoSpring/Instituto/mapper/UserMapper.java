package com.ProyectoSpring.Instituto.mapper;

import com.ProyectoSpring.Instituto.dto.response.UsuarioDTO;
import com.ProyectoSpring.Instituto.entidad.Usuario;

import java.util.List;

public class UserMapper {

    // --- MÉTODO BASE ---
    private static UsuarioDTO convertBase(Usuario user) {
        if (user == null) return null;

        UsuarioDTO userDTO = new UsuarioDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellido(user.getApellido());
        userDTO.setRol(user.getRol());
        userDTO.setEmail(user.getEmail());

        if (user.getAlumno() != null) {
            userDTO.setAlumno(AlumnoMapper.toDto(user.getAlumno()));
        }

        return userDTO;
    }

    // --- VARIANTES ---
    public static UsuarioDTO toDTO(Usuario user) {
        return convertBase(user);
    }

    public static UsuarioDTO toDTOWithToken(Usuario user, String token, String expirationTime) {
        UsuarioDTO dto = convertBase(user);
        dto.setToken(token);
        dto.setTokenExpirationTime(expirationTime);
        return dto;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> users) {
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

}
