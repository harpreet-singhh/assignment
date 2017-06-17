package com.assignment.cart.resource.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.cart.config.exception.ShoppingCartNotFoundException;

@ControllerAdvice
public class ShoppingCartExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ShoppingCartNotFoundException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new com.assignment.cart.model.Error(ex.getMessage(), HttpStatus.NOT_FOUND.name(), "Error"),
				new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
