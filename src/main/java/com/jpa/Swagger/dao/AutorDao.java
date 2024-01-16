package com.jpa.Swagger.dao;

import com.jpa.Swagger.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//es opcional poner el repository ya q al poner extends ya lo reconoce como un dai y por ende
//q es un repositorio
@Repository
public interface AutorDao extends JpaRepository<AutorEntity,Long> {
}
