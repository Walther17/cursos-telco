package com.demo.olimacservices.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.olimacservices.entity.Creador;
import com.demo.olimacservices.repository.CreadorRepository;


@Service
@Transactional
public class CreadorService {
    
     @Autowired
    CreadorRepository creadorRepository;


    public List<Creador> getAll(){
        return creadorRepository.findAll();
    }

    public Optional<Creador> getOne(int id){
        return creadorRepository.findById(id);
    }

    public Optional<Creador> getByNombre(String nombre){
        return creadorRepository.findByNombreCreador(nombre);
    }   

}

