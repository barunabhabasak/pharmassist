package com.jsp.pharmassist.exception;

public class AdminNotAuthenciatedException extends RuntimeException {
	
	public AdminNotAuthenciatedException(String message) {
		super();
		this.message = message;
	}

	private final String message;

	public String getMessage() {
		return message;
	}

}
