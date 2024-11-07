package com.jsp.pharmassist.exception;

@SuppressWarnings("serial")
public class InvalidFileFormatException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public InvalidFileFormatException(String message) {
		super();
		this.message = message;
	}
	
	

}
