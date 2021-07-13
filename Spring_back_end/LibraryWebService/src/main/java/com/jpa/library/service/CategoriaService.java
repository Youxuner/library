package com.jpa.library.service;

import org.springframework.http.HttpStatus;

public interface CategoriaService {
	HttpStatus registraCategoria(String categoria);
	HttpStatus eliminaCategoria(String categoria);
}
