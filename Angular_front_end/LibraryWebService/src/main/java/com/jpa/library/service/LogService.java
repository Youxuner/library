package com.jpa.library.service;

import java.time.LocalDateTime;
import java.util.List;

import com.jpa.library.bean.LogWebOperation;

public interface LogService {
	void registraLogElement(LogWebOperation log);
	List<LogWebOperation> statisticheNelPeriodo(LocalDateTime ldt, String status);
}
