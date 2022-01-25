package com.Football.Tournament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//This class will handle Global Exceptions
@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		return new ResponseEntity<String>("Request method not supported,please change the method.", HttpStatus.BAD_REQUEST);
	}

}
