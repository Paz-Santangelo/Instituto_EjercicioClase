package com.ProyectoSpring.Instituto.controlador;

import com.ProyectoSpring.Instituto.dto.request.LoginDTO;
import com.ProyectoSpring.Instituto.dto.response.UsuarioDTO;
import com.ProyectoSpring.Instituto.entidad.Usuario;
import com.ProyectoSpring.Instituto.servicio.IUsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
    @Autowired
    private IUsuarioServicio usuarioServicio;

    /**
     * Registra un nuevo usuario en el sistema
     * @param user Datos del usuario a registrar
     * @return UsuarioDTO con los datos del usuario registrado
     */
    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody Usuario user) {
        UsuarioDTO usuarioDTO = usuarioServicio.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    /**
     * Autentica un usuario y devuelve un token JWT
     * @param loginDto Email y password del usuario
     * @return UsuarioDTO con token JWT incluido
     */
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDto) {
        UsuarioDTO usuarioDTO = usuarioServicio.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }
}