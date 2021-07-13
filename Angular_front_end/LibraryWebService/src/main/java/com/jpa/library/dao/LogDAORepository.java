package com.jpa.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.library.bean.LogWebOperation;

@Repository
public interface LogDAORepository extends JpaRepository<LogWebOperation, Integer> {

}
