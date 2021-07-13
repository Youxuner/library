package com.jpa.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.library.bean.Autore;
import com.jpa.library.bean.LibroDTO;
import com.jpa.library.service.AutoreService;
import com.jpa.library.service.LibroService;

@CrossOrigin
@RestController
@RequestMapping("/authors")
public class AutoreController {

	private static final String baseURL = "/authors";	// if change this path, please also change the variable baseURL
	
	@Autowired
	private AutoreService autoreServ;
	
	@Autowired
	private LibroService libroServ;
	
	@PostMapping
	public ResponseEntity<Void> registraAutore(@RequestBody Autore a)
	{
		String path = baseURL;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = null;
		
		if (!a.passCheck())
			status = HttpStatus.BAD_REQUEST;
		else
			status =  autoreServ.registraAutore(a);
		
		return new ResponseEntity<Void>(header, status);
	}
	
	@GetMapping(path = "/{idAutore}/books", produces = "application/json")
	public ResponseEntity<List<LibroDTO>> selezionaLibriPerAutore(@PathVariable int idAutore)
	{
		String path = baseURL + "/" + idAutore + "/books";
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = HttpStatus.OK;
		List<LibroDTO> list = libroServ.selezionaLibriPerAutore(idAutore);
		if (list == null)
			status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<List<LibroDTO>>(list, header, status);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Autore> modificaDatiAnagraficiAutore(@PathVariable int id, @RequestBody Autore a) {
		String path = baseURL + "/" + id;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = null;
		
		if (!a.passCheck())
			status = HttpStatus.BAD_REQUEST;
		else
			status =  autoreServ.modificaDatiAnagraficiAutore(a);
		
		Autore autore = status == HttpStatus.OK ? a : null;
		
		return new ResponseEntity<Autore>(autore, header, status);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Autore> eliminaAutore(@PathVariable int id)
	{
		String path = baseURL + "/" + id;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = null;
		
		Autore autore = autoreServ.eliminaAutore(id);
		status = autore == null ? HttpStatus.NOT_FOUND : HttpStatus.NO_CONTENT;
		
		return new ResponseEntity<Autore>(autore, header, status);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Autore> leggiDatiAutore(@PathVariable int id)
	{
		String path = baseURL + "/" + id;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = null;
		
		Autore autore = autoreServ.leggiDatiAutore(id);
		status = autore == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		
		return new ResponseEntity<Autore>(autore, header, status);
	}
	
	@GetMapping
	public ResponseEntity<?> LeggiAutori()
	{
		String path = baseURL;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = null;
		List<Autore> list = autoreServ.LeggiAutori();
		
		status = list.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		
		return new ResponseEntity<List<Autore>>(list, header, status);
	}
}
