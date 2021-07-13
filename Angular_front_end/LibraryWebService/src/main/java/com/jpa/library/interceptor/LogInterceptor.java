package com.jpa.library.interceptor;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jpa.library.bean.LogWebOperation;
import com.jpa.library.service.LogService;

@Component
@Aspect
public class LogInterceptor {
	
	@Autowired
	private LogService logService;
	
	@AfterReturning(pointcut = "execution( * com.jpa.library.controller.*.*(..))", returning = "response")
	public void logInterceptor(JoinPoint jPoint, @SuppressWarnings("rawtypes") ResponseEntity response) {

		HttpHeaders headers = response.getHeaders();
		String path = headers.getFirst("path");
		LocalDateTime dataOra = LocalDateTime.now();
		LogWebOperation log = new LogWebOperation();
		log.setDataOra(dataOra);
		log.setHttpStatus(response.getStatusCode().name());
		log.setUrlRequest(path);
		log.setMethodName(jPoint.getSignature().getName());
		logService.registraLogElement(log);
	}
}
