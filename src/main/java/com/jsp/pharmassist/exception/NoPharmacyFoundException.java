package com.jsp.pharmassist.exception;

public class NoPharmacyFoundException extends RuntimeException{
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public NoPharmacyFoundException(String message) {
		super();
		this.message = message;
	}

	
	

}
