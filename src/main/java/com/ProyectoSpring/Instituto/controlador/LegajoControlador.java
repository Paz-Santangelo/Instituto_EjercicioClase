package com.ProyectoSpring.Instituto.controlador;

import com.ProyectoSpring.Instituto.entidad.Legajo;
import com.ProyectoSpring.Instituto.servicio.ILegajoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Legajos", description = "Operaciones para gestionar legajos")
@RestController
@RequestMapping("/api/legajos")
public class LegajoControlador {

    @Autowired
    private ILegajoServicio legajoServ;

    @Operation(summary = "Guardar un legajo con id alumno mediante parámetro")
    @PostMapping("/guardar/{idAlumno}")
    public ResponseEntity<String> guardarLegajo(@RequestBody Legajo legajo, @PathVariable Long idAlumno) {
        legajoServ.guardarLegajoParametro(legajo, idAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body("Legajo guardado correctamente");
    }

    @Operation(summary = "Guardar un legajo con alumno en el cuerpo de solicitud")
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarLegajoCuerpo(@RequestBody Legajo legajo) {
        legajoServ.guardarLegajoCuerpo(legajo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Legajo guardado correctamente");
    }

    @Operation(summary = "Obtener todos los legajos")
    @GetMapping("/obtener/todos")
    public List<Legajo> listarTodos() {
        return legajoServ.listarTodos();
    }

    @Operation(summary = "Obtener un legajo por su id")
    @GetMapping("/obtener/{id}")
    public Legajo buscarPorId(@PathVariable Long id) {
        return legajoServ.buscarPorId(id);
    }

    @Operation(summary = "Eliminar un legajo por su id")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarLegajo(@PathVariable Long id) {
        legajoServ.eliminarLegajo(id);
        return ResponseEntity.ok("Legajo eliminado correctamente");
    }
}
