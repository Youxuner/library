package com.jpa.library.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.library.bean.LogWebOperation;
import com.jpa.library.dao.LogDAORepository;

@Transactional(propagation = Propagation.REQUIRED)
@Service("LogService")
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDAORepository logDAO;
	
	@Override
	public void registraLogElement(LogWebOperation log) {
		logDAO.save(log);
		
	}

	@Override
	public List<LogWebOperation> statisticheNelPeriodo(LocalDateTime ldt, String status) {
		List<LogWebOperation> l = logDAO.findAll();
		if (ldt != null)
			l = l.stream().filter(log -> log.getDataOra().isAfter(ldt)).collect(Collectors.toList());
		if (status != null)
			l = l.stream().filter(log -> log.getHttpStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		return l;
	}

}
