package com.jpa.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.library.service.CategoriaService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoriaController {

	@Autowired
	private CategoriaService service;
	
	private static final String baseURL = "/category";	// if change this path, please also change the variable baseURL
	
	@PostMapping
	public ResponseEntity<Void> registraCategoria(@RequestParam String categoria)
	{
		String path = baseURL;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = service.registraCategoria(categoria);
		
		return new ResponseEntity<Void>(header, status);
	}
	
	@DeleteMapping(path = "/{categoria}")
	public ResponseEntity<Void> eliminaCategoria(@PathVariable String categoria)
	{
		String path = baseURL;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = service.eliminaCategoria(categoria);
		
		return new ResponseEntity<Void>(header, status);
	}
}
