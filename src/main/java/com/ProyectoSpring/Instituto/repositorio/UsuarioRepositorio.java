package com.ProyectoSpring.Instituto.repositorio;

import com.ProyectoSpring.Instituto.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
