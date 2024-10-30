package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;
import com.jsp.pharmassist.service.PharmcyService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class PharmcyController {
	
	private final PharmcyService pharmcyService;
	private final AppResponseBuilder builder;
	
	
	public PharmcyController(PharmcyService pharmcyService, AppResponseBuilder builder) {
		super();
		this.pharmcyService = pharmcyService;
		this.builder = builder;
	}


	@PostMapping("/pharmacies")
	public ResponseEntity<ResponseStructure<PharmcyResponse>> createPharmcy(@RequestBody @Valid PharmacyRequest pharmcyRequest) {
		PharmcyResponse response = pharmcyService.addPharmcy(pharmcyRequest);
		return builder.success(HttpStatus.CREATED, "PharmcyS created successfully", response);

	}
	

}
