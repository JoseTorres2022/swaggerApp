package com.jpa.Swagger.dao;

import com.jpa.Swagger.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDao extends JpaRepository<CategoriaEntity,Long> {
}
