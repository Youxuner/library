package com.jpa.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.library.bean.Autore;
import com.jpa.library.dao.AutoreDAORepository;

@Transactional(propagation = Propagation.REQUIRED)
@Service("AutoreService")
public class AutoreServiceImpl implements AutoreService{

	@Autowired
	private AutoreDAORepository autoreDAO;
	
	@Override
	public HttpStatus registraAutore(Autore a) {
		if (autoreDAO.existsById(a.getIdAutore()))
			return HttpStatus.CONFLICT;
		
		autoreDAO.save(a);
		return HttpStatus.CREATED;
		
	}

	@Override
	public HttpStatus modificaDatiAnagraficiAutore(Autore a) {
		if (!autoreDAO.existsById(a.getIdAutore()))
			return HttpStatus.NOT_FOUND;
		
		autoreDAO.save(a);
		return HttpStatus.OK;
	}

	@Override
	public Autore eliminaAutore(int id) {
		if (!autoreDAO.existsById(id))
			return null;
		
		Autore autore = autoreDAO.getById(id);
		autoreDAO.deleteById(id);
		
		return autore;
	}

	@Override
	public Autore selezionaAutorePerLibro(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Autore leggiDatiAutore(int id) {
		if (!autoreDAO.existsById(id))
			return null;
		
		return autoreDAO.getById(id);
	}

	@Override
	public List<Autore> LeggiAutori() {
		return autoreDAO.findAll();
	}

}
