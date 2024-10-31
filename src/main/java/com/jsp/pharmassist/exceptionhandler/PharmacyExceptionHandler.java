package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.exception.NoAdminFoundException;
import com.jsp.pharmassist.exception.NoPharmacyFoundException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;

@RestControllerAdvice
public class PharmacyExceptionHandler {
	
	private final AppResponseBuilder builder;

	public PharmacyExceptionHandler(AppResponseBuilder builder) {
		super();
		this.builder = builder;
	}
	
	@ExceptionHandler(NoPharmacyFoundException.class)
	public ResponseEntity<ErrorStructure> handleNoPhramcyFound(NoPharmacyFoundException ex){
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Phramacy are not found within the given criteria");
	}
	@ExceptionHandler(PharmacyNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> handlePhrmacyNotFound(PharmacyNotFoundByIdException ex){
		return builder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Phramacy is not found by id");
	}

}
