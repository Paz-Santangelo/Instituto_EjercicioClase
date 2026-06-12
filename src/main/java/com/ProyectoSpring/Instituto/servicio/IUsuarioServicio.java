package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.LoginDTO;
import com.ProyectoSpring.Instituto.dto.response.UsuarioDTO;
import com.ProyectoSpring.Instituto.entidad.Usuario;

import java.io.IOException;
import java.util.List;

public interface IUsuarioServicio {
    public UsuarioDTO register(Usuario user);

    public UsuarioDTO login(LoginDTO loginDto);

    public List<UsuarioDTO> getAllUsers();

    public UsuarioDTO getUserById(Long id);

    public UsuarioDTO getUserByEmail(String email);

    public void deleteUserById(Long id) throws IOException;

    public UsuarioDTO updateUserRole(Long idUser, String newRole);
}
