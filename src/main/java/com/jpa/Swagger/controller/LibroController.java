package com.jpa.Swagger.controller;

import com.jpa.Swagger.entity.LibroEntity;
import com.jpa.Swagger.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping()
    public List<LibroEntity> listarLibros(){
        List<LibroEntity> libros = libroService.obtenerTodos();
        return libros;
    }

    @GetMapping("/{id}")
    public LibroEntity obtenerLibroPorId(@PathVariable Long id) throws Exception {
        LibroEntity libro = libroService.obtenerPorId(id);
        return libro;
    }

    @PostMapping
    public ResponseEntity<LibroEntity> crearLibro(@RequestBody LibroEntity libro){
        LibroEntity nuevoLibro = libroService.crear(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroEntity> actualizar(@PathVariable Long id, @RequestBody LibroEntity libro) throws Exception {
        LibroEntity libroActualizado = libroService.actualizar(id,libro);
        if (libroActualizado!=null){
            return ResponseEntity.ok(libroActualizado);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws Exception {
        String libroEliminado = libroService.eliminar(id);
        if (libroEliminado != null){
            return ResponseEntity.ok("Eliminado!");
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
