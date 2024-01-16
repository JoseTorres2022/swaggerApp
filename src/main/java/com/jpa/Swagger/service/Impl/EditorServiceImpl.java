package com.jpa.Swagger.service.Impl;

import com.jpa.Swagger.dao.EditorDao;
import com.jpa.Swagger.entity.CategoriaEntity;
import com.jpa.Swagger.entity.EditorEntity;
import com.jpa.Swagger.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private EditorDao editorDao;

    @Override
    public List<EditorEntity> obtenerTodos() {
        return editorDao.findAll();
    }

    @Override
    public EditorEntity obtenerPorId(Long id) throws Exception {
        Optional<EditorEntity> editor = editorDao.findById(id);
        if (editor.isPresent()){
            return editor.get();
        }else {
            throw new Exception("Error,no existe");
        }
    }

    @Override
    public EditorEntity crear(EditorEntity editor) {
        
        return editorDao.save(editor);
    }

    @Override
    public EditorEntity actualizar(Long id, EditorEntity editor) throws Exception {
        Optional<EditorEntity> editorEncontrado = editorDao.findById(id);
        if (editorEncontrado.isPresent()){
            //obtenemos los valores
            EditorEntity editorUpdate = editorEncontrado.get();
            //seteamos los valores q vienen del requestbody q queremos actualizar
            //en este caso seria el parametro autor q contendra los valores q qeremos actualizar
            editorUpdate.setNombre(editor.getNombre());
            editorUpdate.setEstado(editor.getEstado());
            return editorDao.save(editorUpdate);
        }
        else {
            throw new Exception("Error no existe "+id);
        }
    }

    @Override
    public String eliminar(Long id) throws Exception {
        Optional<EditorEntity> editorEncontrado = editorDao.findById(id);

        //Usando lambda
        return editorEncontrado.map(editor -> {
            editor.setEstado(0);
            editorDao.save(editor);
            return "Eliminado";
        }).orElse(null);
    }
}
