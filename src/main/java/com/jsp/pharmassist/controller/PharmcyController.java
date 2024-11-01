package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;
import com.jsp.pharmassist.service.PharmcyService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;
import com.jsp.pharmassist.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	@Operation(description = "The endpoint can be used to add the pharmacy,checking if the adminId is present",
			responses = {
					@ApiResponse(responseCode = "201", description = "Pharmacy Added successfully"),
					@ApiResponse(responseCode = "404", description = "Failed to Add",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PostMapping("/admins/{adminId}/pharmacies")
	public ResponseEntity<ResponseStructure<PharmcyResponse>> createPharmcy(@RequestBody @Valid PharmacyRequest pharmcyRequest,@PathVariable String adminId) {
		PharmcyResponse response = pharmcyService.addPharmcy(pharmcyRequest,adminId);
		return builder.success(HttpStatus.CREATED, "Pharmcy created successfully", response);
	}

	@Operation(description = "The endpoint can be used to find the pharmacy based on the the Admin ID",
			responses = {
					@ApiResponse(responseCode = "302", description = "Pharmacy Found successfully"),
					@ApiResponse(responseCode = "404", description = "Pharmacy id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping("pharmacies/{adminId}")
	public ResponseEntity<ResponseStructure<PharmcyResponse>> finaPharmacyByAdminId(@PathVariable String adminId) {
		PharmcyResponse response = pharmcyService.finaPharmacyByAdminId(adminId);
		return builder.success(HttpStatus.FOUND, "Pharmcy found successfully", response);
	}

	@Operation(description = "The endpoint can be used to update the pharmacy based on the the Unique ID and admin request",
			responses = {
					@ApiResponse(responseCode = "200", description = "Pharmacy Updated successfully"),
					@ApiResponse(responseCode = "404", description = "Pharmacy id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PutMapping("/pharmacies/{pharmacyId}")
	public ResponseEntity<ResponseStructure<PharmcyResponse>> updatePharmacy(@RequestBody PharmacyRequest pharmcyRequest,@PathVariable String pharmacyId ){
		PharmcyResponse response = pharmcyService.updatePharmacyById(pharmcyRequest,pharmacyId);
		return builder.success(HttpStatus.OK, "Pharmacy Upadted successfully", response);
	}



}
