package com.jsp.pharmassist.util;

import org.springframework.http.HttpStatus;

public class SimpleResponseStructure {
	
	private int statuscode;
	private String message;
	
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
	
	public static SimpleResponseStructure create(HttpStatus status, String message) {
		
		SimpleResponseStructure structure = new SimpleResponseStructure();
		structure.setStatuscode(status.value());
		structure.setMessage(message);
		
		return structure;
	} 

}
