package com.jpa.Swagger.dao;

import com.jpa.Swagger.entity.EditorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorDao extends JpaRepository<EditorEntity,Long> {
}
