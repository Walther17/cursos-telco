package com.demo.olimacservices.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "creador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Creador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   
    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;

    
    // Relación uno a muchos con la entidad Curso
    @OneToMany(mappedBy = "creador")
    private List<Curso> cursos;
    
}

