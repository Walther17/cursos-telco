package com.demo.olimacservices.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.olimacservices.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    Optional<Curso> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    
     @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.estado = null WHERE u.id = ?1 ")
    public void setEstadoNull(Integer id);
}
