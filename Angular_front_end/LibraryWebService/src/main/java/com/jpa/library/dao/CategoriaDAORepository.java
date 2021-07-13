package com.jpa.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.library.bean.Categoria;

@Repository
public interface CategoriaDAORepository extends JpaRepository<Categoria, String> {

}
