package com.praksah.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.praksah.exception.CustomException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> handleCustomException(CustomException customexception){
		return new ResponseEntity<>(customexception.getErrCode(), customexception.getHttpStatuCode());
	}

}
