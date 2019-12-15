/*
 * The purpose of this class is to make all exceptions uniform across the application.
 * The general exception response has a lot of information which might not be needed in the aplication front end.
 * By adding a custom exception response, we can send a pre defined template for exception (ExceptionFormat.java). 
 * For all exceptions, this information (timestamp, message, details) will be the only one that is send.
 */

package com.aamer.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice  	// To make this applicable for available Controllers in the application.
					// Scenarios that are handled by this are related to exceptions, dates (init binder) and model attribute
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	// for all generic errors
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionFormat exception = new ExceptionFormat(new Date(),ex.getMessage(), request.getDescription(false));
		 return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
		
	}
	
	
	// for all custom application errors
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
		ExceptionFormat exception = new ExceptionFormat(new Date(),ex.getMessage(), request.getDescription(false));
		 return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}
	
	// for all validation exceptions
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionFormat exception = new ExceptionFormat(new Date(),"Validation Error", ex.getBindingResult().toString());
		 return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}
	
}
