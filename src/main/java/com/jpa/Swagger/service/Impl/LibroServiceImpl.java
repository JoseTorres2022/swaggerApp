package com.jpa.Swagger.service.Impl;

import com.jpa.Swagger.dao.LibroDao;
import com.jpa.Swagger.entity.CategoriaEntity;
import com.jpa.Swagger.entity.EditorEntity;
import com.jpa.Swagger.entity.LibroEntity;
import com.jpa.Swagger.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    public List<LibroEntity> obtenerTodos() {
        return libroDao.findAll();
    }

    @Override
    public LibroEntity obtenerPorId(Long id) throws Exception {
        Optional<LibroEntity> libro = libroDao.findById(id);
        if (libro.isPresent()){
            return libro.get();
        }else {
            throw new Exception("Error,no existe");
        }
    }

    @Override
    public LibroEntity crear(LibroEntity libro) {
        return libroDao.save(libro);
    }

    @Override
    public LibroEntity actualizar(Long id, LibroEntity libro) throws Exception {
        Optional<LibroEntity> libroEncontrado = libroDao.findById(id);
        if (libroEncontrado.isPresent()){
            //obtenemos los valores
            LibroEntity libroUpdate = libroEncontrado.get();
            libroUpdate.setTitulo(libro.getTitulo());
            libroUpdate.setEstado(libro.getEstado());
            libroUpdate.setEditor(libro.getEditor());
            libroUpdate.setAutores(libro.getAutores());
            libroUpdate.setCategorias(libro.getCategorias());
            //seteamos los valores q vienen del requestbody q queremos actualizar
            //en este caso seria el parametro autor q contendra los valores q qeremos actualizar
            return libroDao.save(libroUpdate);
        }
        else {
            throw new Exception("Error no existe "+id);
        }
    }

    @Override
    public String eliminar(Long id) throws Exception {
        Optional<LibroEntity> libroEncontrado = libroDao.findById(id);

        //Usando lambda
        return libroEncontrado.map(libro -> {
            libro.setEstado(0);
            libroDao.save(libro);
            return "Eliminado";
        }).orElse(null);
    }
}
