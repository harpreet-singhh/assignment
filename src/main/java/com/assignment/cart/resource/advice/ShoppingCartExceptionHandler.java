package com.assignment.cart.resource.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.cart.exception.ResourceNotFoundException;

@ControllerAdvice
public class ShoppingCartExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<com.assignment.cart.model.Error> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<com.assignment.cart.model.Error>(
				new com.assignment.cart.model.Error(ex.getMessage(), HttpStatus.NOT_FOUND.name(), "Error"),
				new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
