package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PharmcyController {
	
	private final PharmcyService pharmcyService;
	private final AppResponseBuilder builder;
	
	
	public PharmcyController(PharmcyService pharmcyService, AppResponseBuilder builder) {
		super();
		this.pharmcyService = pharmcyService;
		this.builder = builder;
	}


	@PostMapping("/admins/{adminId}/pharmacies")
	public ResponseEntity<ResponseStructure<PharmcyResponse>> createPharmcy(@RequestBody @Valid PharmacyRequest pharmcyRequest,@PathVariable String adminId) {
		PharmcyResponse response = pharmcyService.addPharmcy(pharmcyRequest,adminId);
		return builder.success(HttpStatus.CREATED, "Pharmcy created successfully", response);
	}
	
	
	
  

}
