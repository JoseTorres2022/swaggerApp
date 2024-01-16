package com.jpa.Swagger.service.Impl;

import com.jpa.Swagger.dao.CategoriaDao;
import com.jpa.Swagger.entity.AutorEntity;
import com.jpa.Swagger.entity.CategoriaEntity;
import com.jpa.Swagger.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<CategoriaEntity> obtenerTodos() {
        return categoriaDao.findAll();
    }

    @Override
    public CategoriaEntity obtenerPorId(Long id) throws Exception {
        Optional<CategoriaEntity> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()){
            return categoria.get();
        }else {
            throw new Exception("Error,no existe");
        }
    }

    @Override
    public CategoriaEntity crear(CategoriaEntity categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public CategoriaEntity actualizar(Long id, CategoriaEntity categoria) throws Exception {
        Optional<CategoriaEntity> categoriaEncontrado = categoriaDao.findById(id);
        if (categoriaEncontrado.isPresent()){
            //obtenemos los valores
            CategoriaEntity categoriaUpdate = categoriaEncontrado.get();
            //seteamos los valores q vienen del requestbody q queremos actualizar
            //en este caso seria el parametro autor q contendra los valores q qeremos actualizar
            categoriaUpdate.setNombre(categoria.getNombre());
            categoriaUpdate.setEstado(categoria.getEstado());
            return categoriaDao.save(categoriaUpdate);
        }
        else {
            throw new Exception("Error no existe "+id);
        }
    }

    @Override
    public String eliminar(Long id) throws Exception {
        Optional<CategoriaEntity> categoriaEncontrado = categoriaDao.findById(id);

        //Usando lambda
        return categoriaEncontrado.map(categoria -> {
            categoria.setEstado(0);
            categoriaDao.save(categoria);
            return "Eliminado";
        }).orElse(null);
    }
}
