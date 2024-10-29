package com.jsp.pharmassist.util;

import org.springframework.http.HttpStatus;

public class ResponseStructure<T> {
	private int statuscode;
	private String message;
	private T data;
	
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public static <T> ResponseStructure<T> create(HttpStatus status,String message, T user){
		
		ResponseStructure<T> structure = new ResponseStructure<T>();
		structure.setData(user);
		structure.setMessage(message);
		structure.setStatuscode(status.value());
		
		return structure;
	}
}
