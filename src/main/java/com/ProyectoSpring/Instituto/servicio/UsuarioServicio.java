package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.LoginDTO;
import com.ProyectoSpring.Instituto.dto.response.RolDTO;
import com.ProyectoSpring.Instituto.dto.response.UsuarioDTO;
import com.ProyectoSpring.Instituto.entidad.Usuario;
import com.ProyectoSpring.Instituto.error.NoEncontradoExcepcion;
import com.ProyectoSpring.Instituto.mapper.UserMapper;
import com.ProyectoSpring.Instituto.repositorio.UsuarioRepositorio;
import com.ProyectoSpring.Instituto.seguridad.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UsuarioDTO register(Usuario user) {

        if (usuarioRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Ya existe un usuario registrado con el email: " + user.getEmail());
        }

        if (user.getRol() == null || user.getRol().isBlank()) {
            user.setRol("ALUMNO");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Usuario userSaved = usuarioRepo.save(user);
        UsuarioDTO userDTO = UserMapper.toDTO(userSaved);
        return userDTO;
    }

    @Override
    public UsuarioDTO login(LoginDTO loginDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        Usuario user = usuarioRepo.findByEmail(loginDto.getEmail());
        if (user == null) throw new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado. Verifica el email ingresado.");

        String token = jwtUtils.generateToken(user);

        return UserMapper.toDTOWithToken(user, token, "7 Days");
    }

    @Override
    public List<UsuarioDTO> getAllUsers() {
        List<Usuario> users = usuarioRepo.findAll();
        return UserMapper.toDTOList(users);
    }

    @Override
    public UsuarioDTO getUserById(Long id) {
        Usuario user = usuarioRepo.findById(id)
                .orElseThrow(() -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado."));

        return UserMapper.toDTO(user);
    }

    @Override
    public UsuarioDTO getUserByEmail(String email) {
        Usuario user = usuarioRepo.findByEmail(email);
        if (user == null) throw new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado.");

        return UserMapper.toDTO(user);
    }

    @Override
    public void deleteUserById(Long id) throws IOException {
        Usuario userFound = usuarioRepo.findById(id)
                .orElseThrow(() -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado."));
        usuarioRepo.deleteById(userFound.getId());
    }

    @Override
    public UsuarioDTO updateUserRole(Long idUser, String newRole) {
        Usuario user = usuarioRepo.findById(idUser)
                .orElseThrow(
                        () -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado."));

        if (!RolDTO.isValidRole(newRole)) {
            throw new RuntimeException("Rol inválido: " + newRole + ". Roles válidos: " + RolDTO.getValidRoles());
        }

        user.setRol(newRole);
        Usuario updatedUser = usuarioRepo.save(user);

        return UserMapper.toDTO(updatedUser);
    }

}
