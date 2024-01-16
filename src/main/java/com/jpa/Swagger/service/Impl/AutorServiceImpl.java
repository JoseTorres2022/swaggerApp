package com.jpa.Swagger.service.Impl;

import com.jpa.Swagger.dao.AutorDao;
import com.jpa.Swagger.entity.AutorEntity;
import com.jpa.Swagger.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorDao autorDao;

    @Override
    public List<AutorEntity> obtenerTodos()
    {
        return autorDao.findAll();
    }

    @Override
    public AutorEntity obtenerPorId(Long id) throws Exception {
        //Se pone optional por q el findById nos retorna un optional
        Optional<AutorEntity> autor = autorDao.findById(id);
        if (autor.isPresent()){
            return autor.get();
        }else {
            throw new Exception("Error no existe");
        }
    }

    @Override
    public AutorEntity crear(AutorEntity autor) {
        return autorDao.save(autor);
    }

    @Override
    public AutorEntity actualizar(Long id, AutorEntity autor) throws Exception {
        //Buscar por id si tenemos ese registro
        Optional<AutorEntity> autorEncontrado = autorDao.findById(id);
        if (autorEncontrado.isPresent()){
            //obtenemos los valores
            AutorEntity autorUpdate = autorEncontrado.get();
            //seteamos los valores q vienen del requestbody q queremos actualizar
            //en este caso seria el parametro autor q contendra los valores q qeremos actualizar
            autorUpdate.setNombre(autor.getNombre());
            autorUpdate.setEstado(autor.getEstado());
            return autorDao.save(autorUpdate);
        }
        else {
            throw new Exception("Error no existe "+id);
        }
    }


    @Override
    public String eliminar(Long id) throws Exception {
        Optional<AutorEntity> autorEncontrado = autorDao.findById(id);

        //Usando lambda
         return autorEncontrado.map(autor -> {
            autor.setEstado(0);
            autorDao.save(autor);
            return "Eliminado";
        }).orElse(null);

        /* Segunda forma sin usar lambda
         if (autorEncontrado.isPresent()){
            AutorEntity autorUpdateEstado = autorEncontrado.get();
            autorUpdateEstado.setEstado(0);
            autorDao.save(autorUpdateEstado);
            return "Autor eliminado";
        }else {
            throw new Exception("Error no existe "+id);
        }
         */
    }
}
