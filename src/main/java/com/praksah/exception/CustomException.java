package com.praksah.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -3209785870232943800L;
	
	private String errCode;
	private Throwable cause;
	private HttpStatus httpStatuCode;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String errCode, HttpStatus httpStatuCode) {
		super(errCode);
		this.errCode = errCode;
		this.httpStatuCode = httpStatuCode;
	}
	
	public CustomException(Throwable cause) {
		super(cause);
		this.cause = cause;
	}
	
	public CustomException(String errCode, Throwable cause) {
		super(errCode, cause);
		this.errCode = errCode;
		this.cause = cause;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public HttpStatus getHttpStatuCode() {
		return httpStatuCode;
	}

	public void setHttpStatuCode(HttpStatus httpStatuCode) {
		this.httpStatuCode = httpStatuCode;
	}
	

}
