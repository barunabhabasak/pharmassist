package com.jsp.pharmassist.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pharmassist.exception.InvalidDataException;
import com.jsp.pharmassist.exception.InvalidDateFormatException;
import com.jsp.pharmassist.exception.InvalidFileFormatException;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;

@RestControllerAdvice
public class MedicineExceptionHandler {

	private final AppResponseBuilder appResponseBuilder;

	public MedicineExceptionHandler(AppResponseBuilder appResponseBuilder) {
		super();
		this.appResponseBuilder = appResponseBuilder;
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ErrorStructure> handleDateFormat(InvalidDateFormatException ex){
		return appResponseBuilder.error(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "Date is not in yyyy-MM-dd format");
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorStructure> handleDateInvalid(InvalidDataException ex){
		return appResponseBuilder.error(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), "Data is not in correct format");
	}
	
	@ExceptionHandler(InvalidFileFormatException.class)
	public ResponseEntity<ErrorStructure> handleInvalidFileFomat(InvalidFileFormatException ex){
		return appResponseBuilder.error(HttpStatus.BAD_REQUEST, ex.getMessage(), "File should be in .xlxs format");
	}

}
