package com.jpa.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.library.bean.LogWebOperation;
import com.jpa.library.service.LogService;

@CrossOrigin
@RestController
@RequestMapping("/losa") // if change this path, please also change the variable baseURL
public class LogController {

	private static final String baseURL = "/losa";
	
	@Autowired
	private LogService service;
	
	@GetMapping("/stat/{status}")
	public ResponseEntity<?> statisticheNelPeriodo(@PathVariable String status)
	{
		String path = baseURL + "/" + status;
		HttpHeaders header = new HttpHeaders();
		header.add("path", path);
		
		List<LogWebOperation> l = service.statisticheNelPeriodo(null, status);
		HttpStatus s = l.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		
		return new ResponseEntity<List<LogWebOperation>>(l, header, s);
	}
}
