package com.demo.olimacservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.olimacservices.dto.Mensaje;
import com.demo.olimacservices.entity.Curso;
import com.demo.olimacservices.enums.EstadoCurso;
import com.demo.olimacservices.security.entity.Usuario;
import com.demo.olimacservices.security.service.UsuarioService;
import com.demo.olimacservices.services.CursoService;

@RestController
@RequestMapping("/cursos")
@CrossOrigin("http://localhost:4200")
public class CursoController {

    @Autowired
    CursoService cursoService;

     @Autowired
    UsuarioService usuarioService;

    
    @GetMapping("/listar-cursos")
    public ResponseEntity<List<Curso>> list() {
        return ResponseEntity.ok(cursoService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Curso> getById(@PathVariable("id") int id) {
        if (!cursoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Curso Curso = cursoService.getOne(id).get();
        return new ResponseEntity(Curso, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Curso> getByNombre(@PathVariable("nombre") String nombre) {
        if (!cursoService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Curso Curso = cursoService.getByNombre(nombre).get();
        return new ResponseEntity(Curso, HttpStatus.OK);
    }

  

    
    @PreAuthorize("hasRole('CREADOR')")
    @PostMapping("/create2")
    public ResponseEntity<Mensaje> create2(@RequestBody Curso CursoDto) {
        Mensaje mensaje = cursoService.validarCurso(CursoDto);
        if (mensaje != null) {
            return ResponseEntity.badRequest().body(mensaje);
        }
        Curso Curso = new Curso(CursoDto.getNombre(), CursoDto.getContenido());
        cursoService.save(Curso);
        return ResponseEntity.ok(new Mensaje("Curso creado"));
    }

    @PreAuthorize("hasRole('CREADOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody Curso CursoDto) {
        cursoService.updateCurso(id, CursoDto);
        return ResponseEntity.ok(new Mensaje("Curso actualizado"));
    }


  @PreAuthorize("hasRole('CREADOR')")
  @PutMapping("/delete/{id}")
  public ResponseEntity<?> setEstadoNull(@Valid @PathVariable Integer id) {
    cursoService.setEstadoNull(id);
    Map<String, Object> response = new HashMap<>(); // (Map) para construye el objeto JSON de respuesta.

    response.put("success", true);
    response.put("message", "Estado actualizado a null para el usuario con ID: " + id);

    return ResponseEntity.ok(response); // devuelve una respuesta exitosa con el objeto JSON construido.
  }

  @PreAuthorize("hasRole('CREADOR')")
@PostMapping("/create")
public ResponseEntity<Mensaje> createCurso(@RequestBody Curso cursoDto, @AuthenticationPrincipal Usuario userDetails) {
    Usuario creador = obtenerCreadorDesdeUserDetails(userDetails);

    // Verificar la cantidad de cursos activos del creador
    if (creador.getCursosCreados() != null && creador.getCursosCreados().size() >= 2) {
        return ResponseEntity.badRequest().body(new Mensaje("Ya tienes la cantidad máxima de cursos activos permitidos."));
    }

    Curso curso = new Curso();
    curso.setNombre(cursoDto.getNombre());
    curso.setContenido(cursoDto.getContenido());
    curso.setEstado(EstadoCurso.ACTIVO);
    curso.setCreador(creador);

    cursoService.save(curso);

    return ResponseEntity.ok(new Mensaje("Curso creado"));
}

private Usuario obtenerCreadorDesdeUserDetails(Usuario userDetails) {
    String nombreUsuario = userDetails.getNombreUsuario();
    return usuarioService.getByNombreUsuario(nombreUsuario)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con el rol de CREADOR"));
}

}
