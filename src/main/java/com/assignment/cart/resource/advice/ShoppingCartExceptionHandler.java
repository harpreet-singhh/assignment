package com.assignment.cart.resource.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.cart.exception.ResourceNotFoundException;
import com.assignment.cart.model.Error;

@ControllerAdvice
public class ShoppingCartExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		return new ResponseEntity<Error>(
				new com.assignment.cart.model.Error(ex.getMessage(), HttpStatus.NOT_FOUND.name(), "Error"),
				new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errorList = new ArrayList<Error>();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		Error error = null;
		for (FieldError fieldError : errors) {
			error = new Error(fieldError.getField() + " " + fieldError.getDefaultMessage(), fieldError.getCode(), "Error");
			errorList.add(error);
		}
		return new ResponseEntity<Object>(errorList, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
