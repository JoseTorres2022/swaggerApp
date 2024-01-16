package com.jpa.Swagger.controller;

import com.jpa.Swagger.entity.CategoriaEntity;
import com.jpa.Swagger.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaEntity> listarCategorias(){
        List<CategoriaEntity> categoria = categoriaService.obtenerTodos();
        return categoria;
    }

    //Usamos ResponseEntity<> para devolver una respuesta mas detallada utilizando http
    //personalizamos el codigo http q nos indica el estado de la respuesta en este caso
    //si la busqueda es correcta nos dara un 200 k
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> obtenerCategoriaPorId(@PathVariable Long id) throws Exception {
        CategoriaEntity categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaEntity> crearCategoria(@RequestBody CategoriaEntity categoria){
        CategoriaEntity nuevaCategoria = categoriaService.crear(categoria);
        //devolvemos el objeto guardado y le damos un HttpStatus  q es created
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> actualizar(@PathVariable Long id, @RequestBody CategoriaEntity categoria) throws Exception {
        CategoriaEntity categoriaActualizado = categoriaService.actualizar(id,categoria);
        if (categoriaActualizado!=null){
            return ResponseEntity.ok(categoriaActualizado);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws Exception {
        String categoriaEliminado = categoriaService.eliminar(id);
        if (categoriaEliminado != null){
            return ResponseEntity.ok("Eliminado!");
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
