package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jsp.pharmassist.exception.NoAdminFoundException;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;

public class AdminExceptionHandler {
	
	private final AppResponseBuilder builder;

	public AdminExceptionHandler(AppResponseBuilder builder) {
		super();
		this.builder = builder;
	}
	
	@ExceptionHandler(NoAdminFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoAdminFound(NoAdminFoundException ex){
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Admins are not found within the given criteria");
	}

}
