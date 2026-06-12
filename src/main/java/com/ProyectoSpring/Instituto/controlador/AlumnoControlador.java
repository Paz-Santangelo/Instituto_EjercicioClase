package com.ProyectoSpring.Instituto.controlador;

import com.ProyectoSpring.Instituto.dto.request.AlumnoDtoRequest;
import com.ProyectoSpring.Instituto.dto.response.AlumnoDtoResponse;
import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.servicio.IAlumnoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoControlador {

    @Autowired
    private IAlumnoServicio alumnoServicio;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/guardar-dto")
    public ResponseEntity<String> guardarAlumnoDto(@Valid @RequestBody AlumnoDtoRequest alumno) {
        alumnoServicio.guardarAlumnoDto(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Alumno guardado correctamente");
    }

    @PreAuthorize("hasAuthority('PROFESOR') or hasAuthority('ADMIN')")
    @GetMapping("/obtener/todos")
    public List<Alumno> listarTodos() {
        return alumnoServicio.listarTodos();
    }

    @PreAuthorize("hasAuthority('PROFESOR') or hasAuthority('ADMIN') or hasAuthority('ALUMNO')")
    @GetMapping("/obtener-alumno-dto/{id}")
    public AlumnoDtoResponse buscarPorIdDto(@PathVariable Long id) {
        return alumnoServicio.buscarPorIdDto(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAlumno(@PathVariable Long id) {
        alumnoServicio.eliminarAlumno(id);
    }

    @GetMapping("/buscar/{apellido}")
    public List<Alumno> buscarPorApellido(@PathVariable String apellido) {
        return alumnoServicio.buscarPorApellido(apellido);
    }

    @GetMapping("/obtener/{id}")
    public Alumno buscarPorId(@PathVariable Long id) {
        return alumnoServicio.buscarPorId(id);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Alumno> guardarAlumno1(@RequestBody Alumno alumno) {
        Alumno alumnoGuardado = alumnoServicio.guardarAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoGuardado);
    }

    @PostMapping("/guardar2")
    public ResponseEntity<String> guardarAlumno2(@RequestBody Alumno alumno) {
        alumnoServicio.guardarAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Alumno guardado correctamente");
    }
}
