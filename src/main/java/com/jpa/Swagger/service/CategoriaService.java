package com.jpa.Swagger.service;


import com.jpa.Swagger.entity.AutorEntity;
import com.jpa.Swagger.entity.CategoriaEntity;

import java.util.List;

public interface CategoriaService {
    List<CategoriaEntity> obtenerTodos();
    CategoriaEntity obtenerPorId(Long id) throws Exception;
    CategoriaEntity crear(CategoriaEntity categoria);
    CategoriaEntity actualizar(Long id, CategoriaEntity categoria) throws Exception;
    String eliminar(Long id) throws Exception;
}
