package com.jpa.library.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.jpa.library.bean.Autore;

public interface AutoreService {
	HttpStatus registraAutore(Autore a);
	HttpStatus modificaDatiAnagraficiAutore(Autore a);
	Autore eliminaAutore(int id);
	Autore selezionaAutorePerLibro(int isbn);
	Autore leggiDatiAutore(int id);
	List<Autore> LeggiAutori();

}
