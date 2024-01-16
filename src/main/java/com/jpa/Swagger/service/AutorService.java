package com.jpa.Swagger.service;


import com.jpa.Swagger.entity.AutorEntity;

import java.util.List;

public interface AutorService {

    List<AutorEntity> obtenerTodos();
    AutorEntity obtenerPorId(Long id) throws Exception;
    AutorEntity crear(AutorEntity autor);
    AutorEntity actualizar(Long id,AutorEntity autor) throws Exception;
    String eliminar(Long id) throws Exception;

}
