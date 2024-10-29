package com.jsp.pharmassist.exception;

@SuppressWarnings("serial")
public class AdminNotFoundByIdException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public AdminNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

}
