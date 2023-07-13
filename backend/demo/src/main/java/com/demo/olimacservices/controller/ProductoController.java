package com.demo.olimacservices.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.demo.olimacservices.dto.Mensaje;
import com.demo.olimacservices.dto.ProductoDto;
import com.demo.olimacservices.entity.Producto;
import com.demo.olimacservices.services.ProductoService;

import java.util.List;
@RestController
@RequestMapping("/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list() {
        return ResponseEntity.ok(productoService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre) {
        if (!productoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

  

    
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/create")
    public ResponseEntity<Mensaje> create2(@RequestBody ProductoDto productoDto) {
        Mensaje mensaje = productoService.validarProducto(productoDto);
        if (mensaje != null) {
            return ResponseEntity.badRequest().body(mensaje);
        }
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.save(producto);
        return ResponseEntity.ok(new Mensaje("Producto creado"));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ProductoDto productoDto) {
        productoService.updateProducto(id, productoDto);
        return ResponseEntity.ok(new Mensaje("Producto actualizado"));
    }


    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

}
