package com.jsp.pharmassist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pharmassist.entity.Bill;
import com.jsp.pharmassist.responsedtos.BillResponse;
import com.jsp.pharmassist.service.BillService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;


@RestController
public class BillController {

	private final BillService billService;
	private final AppResponseBuilder appResponseBuilder;

	public BillController(BillService billService, AppResponseBuilder appResponseBuilder) {
		super();
		this.billService = billService;
		this.appResponseBuilder = appResponseBuilder;
	}

	@PostMapping("/bills/generate/{cartId}")
	public ResponseEntity<ResponseStructure<BillResponse>> generateBill(@PathVariable String cartId){
		BillResponse response = billService.generateBill(cartId);
		return appResponseBuilder.success(HttpStatus.OK, "Bill Generated ", response);
	}

	@GetMapping("/bills/findpatientbills/{patientId}")
	public ResponseEntity<ResponseStructure<List<BillResponse>>> findPatientBills(
			@PathVariable String patientId){
		List<BillResponse> patientBills = billService.findPatientBills(patientId);
		return appResponseBuilder.success(HttpStatus.FOUND, "Bill found", patientBills);
	}


}
