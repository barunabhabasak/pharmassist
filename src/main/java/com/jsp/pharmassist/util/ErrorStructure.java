package com.jsp.pharmassist.util;


public class ErrorStructure<T> {
	
	private int statuscode;
	private String message;
	private T rootCause;
	
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getRootCause() {
		return rootCause;
	}
	public void setRootCause(T rootCause) {
		this.rootCause = rootCause;
	}
	
	public static <T> ErrorStructure<T> create(int status, String message, T rootCause) {
		
		ErrorStructure<T> error = new ErrorStructure<T>();
		error.setStatuscode(status);
		error.setMessage(message);
		error.setRootCause(rootCause);
		
		return error;
	}
	

}
