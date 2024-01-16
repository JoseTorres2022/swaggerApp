package com.jpa.Swagger.dao;

import com.jpa.Swagger.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroDao extends JpaRepository<LibroEntity,Long> {
}
