package com.jpa.Swagger.controller;

import com.jpa.Swagger.entity.EditorEntity;
import com.jpa.Swagger.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @GetMapping()
    public List<EditorEntity> listarEditores(){
        List<EditorEntity> editores = editorService.obtenerTodos();
        return editores;
    }

    @GetMapping("/{id}")
    public EditorEntity obtenerEditorPorId(@PathVariable Long id) throws Exception {
        EditorEntity editor = editorService.obtenerPorId(id);
        return editor;
    }

    @PostMapping
    public ResponseEntity<EditorEntity> crearEditor(@RequestBody EditorEntity editor){
        EditorEntity nuevoEditor = editorService.crear(editor);
        return new ResponseEntity<>(nuevoEditor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditorEntity> actualizar(@PathVariable Long id, @RequestBody EditorEntity editor) throws Exception {
        EditorEntity editorActualizado = editorService.actualizar(id,editor);
        if (editorActualizado!=null){
            return ResponseEntity.ok(editorActualizado);
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) throws Exception {
        String editorEliminado = editorService.eliminar(id);
        if (editorEliminado != null){
            return ResponseEntity.ok("Eliminado!");
        }else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
