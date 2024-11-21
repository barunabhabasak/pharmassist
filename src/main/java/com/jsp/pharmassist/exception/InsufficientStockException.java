package com.jsp.pharmassist.exception;

public class InsufficientStockException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public InsufficientStockException(String message) {
		super();
		this.message = message;
	}
	
	

}
