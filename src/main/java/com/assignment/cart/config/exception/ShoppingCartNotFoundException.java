package com.assignment.cart.config.exception;

public class ShoppingCartNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -433443122322426627L;

	public ShoppingCartNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingCartNotFoundException(String message) {
		super(message);
	}

}
