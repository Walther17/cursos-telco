package com.demo.olimacservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.olimacservices.dto.Mensaje;
import com.demo.olimacservices.dto.ProductoDto;
import com.demo.olimacservices.entity.Producto;
import com.demo.olimacservices.repository.ProductoRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getAll(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }   
    public void updateProducto(int id, ProductoDto productoDto) {
    Mensaje mensaje = validarProducto(productoDto);
    if (mensaje != null) {
        throw new IllegalArgumentException(mensaje.getMensaje());
    }

    Optional<Producto> optionalProducto = getOne(id);
    if (!optionalProducto.isPresent()) {
        throw new EntityNotFoundException("No se encontró el producto con ID " + id);
    }

    Producto producto = optionalProducto.get();
    producto.setNombre(productoDto.getNombre());
    producto.setPrecio(productoDto.getPrecio());
    save(producto);
}
 
    public void  save(Producto producto){
        productoRepository.save(producto);
    }
    public Mensaje validarProducto(ProductoDto productoDto) {
        if (Objects.isNull(productoDto.getNombre()) || productoDto.getNombre().trim().isEmpty()) {
            return new Mensaje("El nombre es obligatorio");
        }
        if (Objects.isNull(productoDto.getPrecio()) || productoDto.getPrecio() < 0) {
            return new Mensaje("El precio debe ser mayor que 0");
        }
        if (existsByNombre(productoDto.getNombre())) {
            return new Mensaje("Ese nombre ya existe");
        }
        return null; // Indica que el producto es válido
    }

    public void delete(int id){
        productoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return productoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }


}


