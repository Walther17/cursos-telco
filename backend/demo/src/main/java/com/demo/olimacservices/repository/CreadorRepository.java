package com.demo.olimacservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.olimacservices.entity.Creador;

@Repository
public interface CreadorRepository extends JpaRepository<Creador, Integer> {
    
     Optional<Creador> findByNombreCreador(String nombre);
     
      @Query("SELECT u FROM Creador u WHERE u.id = :id AND u.estado = 'A' ")
    public Creador getCreadorById(Integer id);

     @Query("SELECT u FROM Creador u WHERE u.estado = 'A'")
    public List<Creador> getAllCreadores();

}
