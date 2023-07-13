package com.demo.olimacservices.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.olimacservices.enums.EstadoCurso;
import com.demo.olimacservices.security.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El campo contendido no puede ser nulo")
    @NotBlank(message = "El campo contenido no puede estar vacío")
    private String contenido;


    @Enumerated(EnumType.STRING)
    private EstadoCurso estado;
    
    @ManyToMany(mappedBy = "cursos")
    private List<Usuario> usuarios;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Creador creador;

    public Curso(
            @NotNull(message = "El campo nombre no puede ser nulo") @NotBlank(message = "El campo nombre no puede estar vacío") String nombre,
            @NotNull(message = "El campo contendido no puede ser nulo") @NotBlank(message = "El campo contenido no puede estar vacío") String contenido,
            EstadoCurso estado) {
        this.nombre = nombre;
        this.contenido = contenido;
        this.estado = estado;
    }

    public Curso(
            @NotNull(message = "El campo nombre no puede ser nulo") @NotBlank(message = "El campo nombre no puede estar vacío") String nombre,
            @NotNull(message = "El campo contendido no puede ser nulo") @NotBlank(message = "El campo contenido no puede estar vacío") String contenido,
            EstadoCurso estado, List<Usuario> usuarios, Creador creador) {
        this.nombre = nombre;
        this.contenido = contenido;
        this.estado = estado;
        this.usuarios = usuarios;
        this.creador = creador;
    }

    public Curso(
            @NotNull(message = "El campo nombre no puede ser nulo") @NotBlank(message = "El campo nombre no puede estar vacío") String nombre,
            @NotNull(message = "El campo contendido no puede ser nulo") @NotBlank(message = "El campo contenido no puede estar vacío") String contenido) {
        this.nombre = nombre;
        this.contenido = contenido;
    }

    

    
}

