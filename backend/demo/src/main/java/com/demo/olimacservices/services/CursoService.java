package com.demo.olimacservices.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.olimacservices.dto.Mensaje;
import com.demo.olimacservices.entity.Curso;
import com.demo.olimacservices.enums.EstadoCurso;
import com.demo.olimacservices.repository.CursoRepository;

@Service
@Transactional
public class CursoService {
    
    @Autowired
    CursoRepository cursoRepository;

   public List<Curso> getAll(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> getOne(int id){
        return cursoRepository.findById(id);
    }

    public Optional<Curso> getByNombre(String nombre){
        return cursoRepository.findByNombre(nombre);
    }   
    public void updateCurso(int id, Curso CursoDto) {
    Mensaje mensaje = validarCurso(CursoDto);
    if (mensaje != null) {
        throw new IllegalArgumentException(mensaje.getMensaje());
    }

    Optional<Curso> optionalCurso = getOne(id);
    if (!optionalCurso.isPresent()) {
        throw new EntityNotFoundException("No se encontró el Curso con ID " + id);
    }

    Curso Curso = optionalCurso.get();
    Curso.setNombre(CursoDto.getNombre());
    Curso.setContenido(CursoDto.getContenido());
    save(Curso);
}
public void save(Curso curso) {
    curso.setEstado(EstadoCurso.ACTIVO);
    cursoRepository.save(curso);
}

    public Mensaje validarCurso(Curso CursoDto) {
        if (Objects.isNull(CursoDto.getNombre()) || CursoDto.getNombre().trim().isEmpty()) {
            return new Mensaje("El nombre es obligatorio");
        }
        if (Objects.isNull(CursoDto.getContenido()) || CursoDto.getContenido().trim().isEmpty()) {
            return new Mensaje("El precio debe ser mayor que 0");
        }
        if (existsByNombre(CursoDto.getNombre())) {
            return new Mensaje("Ese nombre ya existe");
        }
        return null; // Indica que el Curso es válido
    }

    public void delete(int id){
        cursoRepository.deleteById(id);
    }

    public void setEstadoNull(Integer id) {
        cursoRepository.setEstadoNull(id);

}

    public boolean existsById(int id){
        return cursoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return cursoRepository.existsByNombre(nombre);
    }

}
