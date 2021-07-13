package com.jpa.library.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.jpa.library.bean.Libro;
import com.jpa.library.bean.LibroDTO;
import com.jpa.library.bean.MagazzinoDTO;

public interface LibroService {

	HttpStatus registraNuovoLibro(Libro libro);
	LibroDTO modificaDatiLibro(Libro libro);
	HttpStatus eliminaLibro(int isbn);
	List<LibroDTO> selezionaLibriPerAutore(int idAutore);
	LibroDTO leggiDatiLibro(int isbn);
	List<LibroDTO> selezionaTuttiLibri(int offset, int page);
	Map<String, Long> statistichePerNazione();
	void approvvigionaLibro(int isbn, int nCopie);
	HttpStatus cambiaStatoRichiesta (int isbn, String stato);
	List<MagazzinoDTO> leggiInfoStock(int isbn);
	HttpStatus chiudiRichiesta(int isbn);
}
