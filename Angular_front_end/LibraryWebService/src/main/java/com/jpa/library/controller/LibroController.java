package com.jpa.library.controller;

import java.util.List;
import java.util.Map;

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

import com.jpa.library.bean.Libro;
import com.jpa.library.bean.LibroDTO;
import com.jpa.library.bean.MagazzinoDTO;
import com.jpa.library.service.LibroService;

@CrossOrigin
@RestController
@RequestMapping("/books")	// if change this path, please also change the variable baseURL
public class LibroController {
	
	private static final String baseURL = "/books";

	@Autowired
	private LibroService service;
	
	@GetMapping
	public ResponseEntity<?> selezionaTuttiLibri() {
		List<LibroDTO> list = service.selezionaTuttiLibri(0, 1);
		HttpHeaders header = new HttpHeaders();
		header.add("path", baseURL);
		
		return new ResponseEntity<List<LibroDTO>>(list, header, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registraNuovoLibro(@RequestBody Libro libro)
	{
		HttpStatus status = service.registraNuovoLibro(libro);
		HttpHeaders header = new HttpHeaders();
		header.add("path", baseURL);
		
		return new ResponseEntity<Void>(header, status);
	}
	
	@PutMapping(path = "/{isbn}")
	public ResponseEntity<LibroDTO> modificaDatiLibro(@PathVariable int isbn, @RequestBody Libro libro)
	{
		String path = baseURL + "/" + isbn;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			return new ResponseEntity<LibroDTO>(lib, header, HttpStatus.NOT_FOUND);
		
		lib = service.modificaDatiLibro(libro);
		return new ResponseEntity<LibroDTO>(lib, header, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{isbn}")
	public ResponseEntity<LibroDTO> eliminaLibro(@PathVariable int isbn)
	{
		String path = baseURL + "/" + isbn;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		LibroDTO libro = service.leggiDatiLibro(isbn);
		if (libro == null)
			return new ResponseEntity<LibroDTO>(libro, header, HttpStatus.NOT_FOUND);
		
		HttpStatus status = service.eliminaLibro(isbn);
		return new ResponseEntity<LibroDTO>(libro, header, status);
	}
	
	@GetMapping(path = "/{isbn}", produces = "application/json")
	public ResponseEntity<LibroDTO> leggiDatiLibro(@PathVariable int isbn)
	{
		String path = baseURL + "/" + isbn;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = HttpStatus.OK;
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<LibroDTO>(lib, header, status);
	}
	
	@GetMapping(path = "/countrystat", produces = "application/json")
	public ResponseEntity<Map<String, Long>> statistichePerNazione()
	{
		String path = baseURL + "/countrystat";
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		Map<String, Long> statistiche = service.statistichePerNazione();
		
		return new ResponseEntity<Map<String, Long>>(statistiche, header, HttpStatus.OK);
	}
	
	/*
	@GetMapping(path = "/forauthor/{idAutore}", produces = "application/json")
	public ResponseEntity<List<LibroDTO>> selezionaLibriPerAutore(@PathVariable int idAutore)
	{
		String path = baseURL + "/forauthor/" + idAutore;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = HttpStatus.OK;
		List<LibroDTO> list = service.selezionaLibriPerAutore(idAutore);
		if (list.isEmpty())
			status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<List<LibroDTO>>(list, header, status);
	}
	*/
	
	@PostMapping(path = "/{isbn}/warehouse/{n}")
	public ResponseEntity<Void> approvvigionaLibro(@PathVariable int isbn, @PathVariable int n)
	{
		String path = baseURL + "/" + isbn + "/warehouse/" + n;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = HttpStatus.OK;
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			status = HttpStatus.NOT_FOUND;
		else
			service.approvvigionaLibro(isbn, n);
		
		return new ResponseEntity<>(header, status);
	}
	
	@PutMapping(path = "/{isbn}/warehouse/{stato}")
	public ResponseEntity<Void> cambiaStatoRichiesta(@PathVariable int isbn, @PathVariable String stato)
	{
		String path = baseURL + "/" + isbn + "/warehouse/" + stato;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status;
		
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			status = HttpStatus.NOT_FOUND;
		else
			status = service.cambiaStatoRichiesta(isbn, stato);
		
		return new ResponseEntity<>(header, status);
	}
	
	@DeleteMapping(path = "/{isbn}/warehouse")
	public ResponseEntity<Void> chiudiRichiesta(@PathVariable int isbn)
	{
		String path = baseURL + "/" + isbn + "/warehouse";
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status;
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			status = HttpStatus.NOT_FOUND;
		else
			status = service.chiudiRichiesta(isbn);
		
		return new ResponseEntity<>(header, status);
	}
	
	@GetMapping(path = "/{isbn}/warehouse")
	public ResponseEntity<?> leggiInfoStock(@PathVariable int isbn)
	{
		String path = baseURL + "/" + isbn + "/warehouse";
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		HttpStatus status = HttpStatus.OK;
		LibroDTO lib = service.leggiDatiLibro(isbn);
		if (lib == null)
			status = HttpStatus.NOT_FOUND;
		
		List<MagazzinoDTO> list = service.leggiInfoStock(isbn);
		return new ResponseEntity<List<MagazzinoDTO>>(list, header, status);
	}
}
