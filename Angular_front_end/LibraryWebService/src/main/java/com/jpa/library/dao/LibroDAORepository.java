package com.jpa.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.library.bean.Libro;

public interface LibroDAORepository extends JpaRepository<Libro, Integer> {

}
