package com.jsp.pharmassist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;
import com.jsp.pharmassist.service.PatientService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;
import com.jsp.pharmassist.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	
	@Operation(description = "The endpoint can be used to add the patient,checking if the pharmacyId is present",
			responses = {
					@ApiResponse(responseCode = "201", description = "Patient Added successfully"),
					@ApiResponse(responseCode = "404", description = "Failed to Add",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PostMapping("pharmacies/{pharmacyId}/patients")
	public ResponseEntity<ResponseStructure<PatientResponse>> addPatient(@RequestBody @Valid PatientRequest patientRequest
			,@PathVariable String pharmacyId){
		
		PatientResponse response = patientService.addPatient(patientRequest,pharmacyId);
		return builder.success(HttpStatus.CREATED, "Patient add successfully", response);
	}
	
	
	@Operation(description = "The endpoint can be used to find all the patient based on the the pharmcy ID",
			responses = {
					@ApiResponse(responseCode = "302", description = "Patient Found successfully"),
					@ApiResponse(responseCode = "404", description = "Patient id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping("patients/{pharmacyId}")
	public ResponseEntity<ResponseStructure<List<PatientResponse>>> findAllPatientsByPharmacyId(
			@PathVariable String pharmacyId){
		List<PatientResponse> response = patientService.findAllPatientsByPharmacyId(pharmacyId);
		return builder.success(HttpStatus.FOUND, "Patient found successfully", response);
	}
	
	
	@Operation(description = "The endpoint can be used to update the patient based on the the Unique ID and patient request",
			responses = {
					@ApiResponse(responseCode = "200", description = "Patient Updated successfully"),
					@ApiResponse(responseCode = "404", description = "Patient id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PutMapping("/patients/{patientId}")
	public ResponseEntity<ResponseStructure<PatientResponse>> updatePharmacy(@RequestBody PatientRequest patientRequest,@PathVariable String patientId ){
		PatientResponse response = patientService.updatePatientById(patientRequest,patientId);
		return builder.success(HttpStatus.OK, "Patient Upadted successfully", response);
	}
	

}
