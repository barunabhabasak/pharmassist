package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;
import com.jsp.pharmassist.service.PatientService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class PatientController {
	
	private final PatientService patientService;
	private final AppResponseBuilder builder;
	
	public PatientController(PatientService patientService, AppResponseBuilder builder) {
		super();
		this.patientService = patientService;
		this.builder = builder;
	}
	
	@PostMapping("pharmacies/{pharmacyId}/patients")
	public ResponseEntity<ResponseStructure<PatientResponse>> addPatient(@RequestBody @Valid PatientRequest patientRequest
			,@PathVariable String pharmacyId){
		
		PatientResponse response = patientService.addPatient(patientRequest,pharmacyId);
		return builder.success(HttpStatus.CREATED, "Patient add successfully", response);
	}


}
