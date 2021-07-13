package com.jpa.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.library.bean.Categoria;
import com.jpa.library.bean.Libro;
import com.jpa.library.dao.CategoriaDAORepository;
import com.jpa.library.dao.LibroDAORepository;

@Transactional(propagation = Propagation.REQUIRED)
@Service("CategoryService")
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDAORepository cateDAO;
	
	@Autowired
	private LibroDAORepository libroDAO;
	
	@Override
	public HttpStatus registraCategoria(String categoria) {
		Categoria cate = new Categoria(categoria);
		if (cateDAO.exists(Example.of(cate)))
			return HttpStatus.CONFLICT;
		
		cateDAO.save(cate);
		return HttpStatus.CREATED;
	}

	@Override
	public HttpStatus eliminaCategoria(String categoria) {
		List<Libro> list = libroDAO.findAll();
		if (list.stream().anyMatch(l -> l.getCategoria().getCategoria().equals(categoria)))
			return HttpStatus.BAD_REQUEST;
		
		Categoria cate = new Categoria(categoria);
		cate = cateDAO.findOne(Example.of(cate)).orElse(null);
		if (cate == null)
			return HttpStatus.BAD_REQUEST;
		
		cateDAO.delete(cate);
		return HttpStatus.NO_CONTENT;
	}

}
