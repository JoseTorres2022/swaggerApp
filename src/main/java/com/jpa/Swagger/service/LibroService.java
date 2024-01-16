package com.jpa.Swagger.service;


import com.jpa.Swagger.entity.AutorEntity;
import com.jpa.Swagger.entity.LibroEntity;

import java.util.List;

public interface LibroService {
    List<LibroEntity> obtenerTodos();
    LibroEntity obtenerPorId(Long id) throws Exception;
    LibroEntity crear(LibroEntity libro);
    LibroEntity actualizar(Long id, LibroEntity libro) throws Exception;
    String eliminar(Long id) throws Exception;
}
