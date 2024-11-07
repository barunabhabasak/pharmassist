package com.jsp.pharmassist.exception;

public class InvalidDataException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public InvalidDataException(String message) {
		super();
		this.message = message;
	}
	
	

}
