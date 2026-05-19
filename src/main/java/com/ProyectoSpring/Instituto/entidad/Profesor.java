package com.ProyectoSpring.Instituto.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Materia> materias;
}
