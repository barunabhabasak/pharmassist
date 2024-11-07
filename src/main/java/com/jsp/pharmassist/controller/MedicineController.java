package com.jsp.pharmassist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.pharmassist.service.MedicineService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.SimpleResponseStructure;

@RestController
public class MedicineController {

	private final AppResponseBuilder appResponseBuilder;
	private final MedicineService medicineService;

	public MedicineController(AppResponseBuilder appResponseBuilder,MedicineService medicineService) {
		this.appResponseBuilder = appResponseBuilder;
		this.medicineService = medicineService;
	}


	@PostMapping("pharmacies/{pharmacyId}/medicines")
	public ResponseEntity<SimpleResponseStructure> uploadMedicines(@RequestParam("medicine_info") MultipartFile file
			,@PathVariable String pharmacyId){
		String msg = medicineService.uploadMedicines(file,pharmacyId);
		return appResponseBuilder.success(HttpStatus.OK, msg);
	}
	

	
}
