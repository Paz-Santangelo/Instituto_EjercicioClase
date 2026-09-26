package com.ProyectoSpring.Instituto.servicio;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.request.AlumnoConLegajoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.entidad.Usuario;
import com.ProyectoSpring.Instituto.error.NoEncontradoExcepcion;
import com.ProyectoSpring.Instituto.mapper.AlumnoMapper;
import com.ProyectoSpring.Instituto.repositorio.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlumnoServicio implements IAlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepo;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Override
    public AlumnoDtoResponse guardarAlumnoDto(AlumnoDtoRequest alumnoDto) {
        Alumno alumnoMapeado = AlumnoMapper.toEntity(alumnoDto);

        Usuario usuario = usuarioServicio.getUserById(alumnoDto.getIdUsuario());
        alumnoMapeado.setUsuario(usuario);

        return AlumnoMapper.toDto(alumnoRepo.save(alumnoMapeado));
    }

    @Override
    @Transactional
    public AlumnoDtoResponse guardarAlumnoLegajo(AlumnoConLegajoRequest dto) {
        Alumno alumno = AlumnoMapper.toEntityConLegajo(dto);

        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioServicio.getUserById(dto.getIdUsuario());
            alumno.setUsuario(usuario);
        }

        alumno.getLegajo().setFechaAlta(LocalDate.now());

        // Alumno es la raíz del agregado y la relación posee cascade = ALL:
        // al guardar el alumno también se persiste su legajo de forma atómica.
        return AlumnoMapper.toDto(alumnoRepo.save(alumno));
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
        alumnoRepo.delete(alumno);
    }

    @Override
    public List<AlumnoDtoResponse> listarTodos() {
        return alumnoRepo.findAll().stream()
                .map(AlumnoMapper::toDto)
                .toList();
    }

    @Override
    public List<Alumno> buscarPorApellido(String apellido) {
        return alumnoRepo.findByApellido(apellido);
    }
}
