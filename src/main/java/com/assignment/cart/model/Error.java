package com.assignment.cart.model;

public class Error {

	private String message;

	private String code;

	private String level;

	public Error() {
		// TODO Auto-generated constructor stub
	}

	public Error(String message, String code, String level) {
		super();
		this.message = message;
		this.code = code;
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
