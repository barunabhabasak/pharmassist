package com.jsp.pharmassist.exception;

public class MedicineNotFoundByIdException extends RuntimeException {
	
	private final String message;

	public String getMessage() {
		return message;
	}

	public MedicineNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
	

}
