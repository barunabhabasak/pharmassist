package com.jsp.pharmassist.exception;

public class NoAdminFoundException extends RuntimeException{
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public NoAdminFoundException(String message) {
		super();
		this.message = message;
	}

	
	

}
