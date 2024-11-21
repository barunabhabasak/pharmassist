package com.jsp.pharmassist.exception;

public class PatientNotFoundById extends RuntimeException{

	private final String message;

	public String getMessage() {
		return message;
	}

	public PatientNotFoundById(String message) {
		super();
		this.message = message;
	}
	
	
}
