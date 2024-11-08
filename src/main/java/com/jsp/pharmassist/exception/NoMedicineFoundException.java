package com.jsp.pharmassist.exception;

public class NoMedicineFoundException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public NoMedicineFoundException(String message) {
		super();
		this.message = message;
	}
	
	

}
