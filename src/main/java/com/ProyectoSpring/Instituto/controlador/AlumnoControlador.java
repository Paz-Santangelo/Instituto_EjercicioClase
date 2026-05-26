package com.ProyectoSpring.Instituto.controlador;

import com.ProyectoSpring.Instituto.entidad.Alumno;
import com.ProyectoSpring.Instituto.servicio.IAlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoControlador {

    @Autowired
    private IAlumnoServicio alumnoServicio;

    @GetMapping("/obtener/todos")
    public List<Alumno> listarTodos() {
        return alumnoServicio.listarTodos();
    }

    @GetMapping("/obtener/{id}")
    public Alumno buscarPorId(@PathVariable Long id) {
        return alumnoServicio.buscarPorId(id);
    }

    @GetMapping("/buscar/{apellido}")
    public List<Alumno> buscarPorApellido(@PathVariable String apellido) {
        return alumnoServicio.buscarPorApellido(apellido);
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

    @DeleteMapping("/eliminar/{id}")
    public void eliminarAlumno(@PathVariable Long id) {
        alumnoServicio.eliminarAlumno(id);
    }
}
