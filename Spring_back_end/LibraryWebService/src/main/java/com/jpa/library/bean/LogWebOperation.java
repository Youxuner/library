package com.jpa.library.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class LogWebOperation {
	
	@Id
	@Column(name = "idLog")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLog;
	
	@NotNull
	@Column(name = "dataOra")
	private LocalDateTime dataOra;
	
	@NotNull
	@Column(name = "urlRequest")
	private String urlRequest;
	
	@NotNull
	@Column(name = "methodName")
	private String methodName;
	
	@NotNull
	@Column(name = "httpStatus")
	private String httpStatus;

	public LogWebOperation() {}

	public LogWebOperation(int idLog, LocalDateTime dataOra, String urlRequest, String methodName, String httpStatus) {
		super();
		this.idLog = idLog;
		this.dataOra = dataOra;
		this.urlRequest = urlRequest;
		this.methodName = methodName;
		this.httpStatus = httpStatus;
	}


	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public String getUrlRequest() {
		return urlRequest;
	}

	public void setUrlRequest(String urlRequest) {
		this.urlRequest = urlRequest;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "LogWebOperation [idLog=" + idLog + ", dataOra=" + dataOra + ", URLRequest=" + urlRequest
				+ ", httpStatus=" + httpStatus + "]";
	}
}


/*
 * LocalDateTime myDateObj = LocalDateTime.now();
 * System.out.println("Before formatting: " + myDateObj); DateTimeFormatter
 * myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
 * 
 * String formattedDate = myDateObj.format(myFormatObj);
 * System.out.println("After formatting: " + formattedDate);
 */