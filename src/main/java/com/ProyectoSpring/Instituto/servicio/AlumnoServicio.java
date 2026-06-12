package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.entidad.Usuario;
import com.ProyectoSpring.Instituto.error.NoEncontradoExcepcion;
import com.ProyectoSpring.Instituto.mapper.AlumnoMapper;
import com.ProyectoSpring.Instituto.repositorio.AlumnoRepositorio;
import com.ProyectoSpring.Instituto.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicio implements IAlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepo;

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    public AlumnoDtoResponse guardarAlumnoDto(AlumnoDtoRequest alumnoDto) {
        Alumno alumnoMapeado = AlumnoMapper.toEntity(alumnoDto);

        Usuario usuario = usuarioRepo.findById(alumnoDto.getIdUsuario())
                .orElseThrow(() -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Usuario no encontrado con id: " + alumnoDto.getIdUsuario()));
        alumnoMapeado.setUsuario(usuario);

        return AlumnoMapper.toDto(alumnoRepo.save(alumnoMapeado));
    }

    @Override
    public AlumnoDtoResponse buscarPorIdDto(Long id) {
        Alumno alumno = alumnoRepo.findById(id)
                .orElseThrow(() -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Alumno no encontrado con id: " + id));

        return AlumnoMapper.toDto(alumno);
    }

    @Override
    public Alumno buscarPorId(Long id) {
        Alumno alumno = alumnoRepo.findById(id)
                .orElseThrow(() -> new NoEncontradoExcepcion(HttpStatus.NOT_FOUND, "Alumno no encontrado con id: " + id));
        return alumno;
    }

    @Override
    public void eliminarAlumno(Long id) {
        Alumno alumno = buscarPorId(id);
        if (alumno != null) {
            alumnoRepo.delete(alumno);
        }
    }

    @Override
    public Alumno guardarAlumno(Alumno alumno) {
        // Validar que el nombre no esté vacío
        if (alumno.getNombre() == null || alumno.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del alumno no puede estar vacío");
        }

        // Validar que el apellido no esté vacío
        if (alumno.getApellido() == null || alumno.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del alumno no puede estar vacío");
        }

        // Si las validaciones pasan, se guarda el alumno
        return alumnoRepo.save(alumno);
    }

    @Override
    public List<Alumno> listarTodos() {
        return alumnoRepo.findAll();
    }

    @Override
    public List<Alumno> buscarPorApellido(String apellido) {
        return alumnoRepo.findByApellido(apellido);
    }
}
