package com.jpa.Swagger.controller;

import com.jpa.Swagger.entity.AutorEntity;
import com.jpa.Swagger.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping()
    public List<AutorEntity> listarAutores(){
        return autorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorEntity> obtenerAutorPorId(@PathVariable Long id) throws Exception {
        AutorEntity autor = autorService.obtenerPorId(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorEntity> crearAutor(@RequestBody AutorEntity autor){
        AutorEntity nuevoAutor = autorService.crear(autor);
        return new ResponseEntity<>(nuevoAutor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorEntity> actualizar(@PathVariable Long id,@RequestBody AutorEntity autor) throws Exception {
        AutorEntity autorActualizado = autorService.actualizar(id,autor);
        if (autorActualizado!=null){
            return ResponseEntity.ok(autorActualizado);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws Exception {
        String autorEliminado = autorService.eliminar(id);
        if (autorEliminado != null){
            return ResponseEntity.ok("Eliminado!");
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
