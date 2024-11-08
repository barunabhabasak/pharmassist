package com.jsp.pharmassist.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.pharmassist.responsedtos.MedicineResponse;
import com.jsp.pharmassist.service.MedicineService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ErrorStructure;
import com.jsp.pharmassist.util.ResponseStructure;
import com.jsp.pharmassist.util.SimpleResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class MedicineController {

	private final AppResponseBuilder appResponseBuilder;
	private final MedicineService medicineService;

	public MedicineController(AppResponseBuilder appResponseBuilder,MedicineService medicineService) {
		this.appResponseBuilder = appResponseBuilder;
		this.medicineService = medicineService;
	}

	@Operation(description = "The endpoint can be used to add the medicine,checking if the pharmacyId is present",
			responses = {
					@ApiResponse(responseCode = "201", description = "Medicine Added successfully"),
					@ApiResponse(responseCode = "404", description = "Failed to Add",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PostMapping("pharmacies/{pharmacyId}/medicines")
	public ResponseEntity<SimpleResponseStructure> uploadMedicines(@RequestParam("medicine_info") MultipartFile file
			,@PathVariable String pharmacyId){
		String msg = medicineService.uploadMedicines(file,pharmacyId);
		return appResponseBuilder.success(HttpStatus.OK, msg);
	}
	
	
	@Operation(description = "The endpoint can be used to find the list medicine based on the the name or ingredients",
			responses = {
					@ApiResponse(responseCode = "302", description = "Medicine Found successfully"),
					@ApiResponse(responseCode = "404", description = "Medicine id not found",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping("medicines/{name}/{ingredients}")
	public ResponseEntity<ResponseStructure<List<MedicineResponse>>> findMedicineByNameOrIngredients(
			@PathVariable String name,@PathVariable String ingredients){
		
		List<MedicineResponse> responses = medicineService.findMedicineByNameOrIngredients(name,ingredients);
		return appResponseBuilder.success(HttpStatus.FOUND, "Medicine found successfully",responses);
		
	}
	
}
