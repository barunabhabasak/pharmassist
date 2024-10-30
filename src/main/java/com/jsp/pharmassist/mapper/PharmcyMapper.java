package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;

@Component
public class PharmcyMapper {
	
	public Pharmacy mapToPharmcy(PharmacyRequest pharmcyRequest, Pharmacy pharmacy) {
		pharmacy.setName(pharmcyRequest.getName());
		pharmacy.setGstNo(pharmcyRequest.getGstNo());
		pharmacy.setLicenceNo(pharmcyRequest.getLicenceNo());

		return pharmacy;
	}

	public PharmcyResponse mapToPharmcyResponse(Pharmacy pharmacy) {
		PharmcyResponse response = new PharmcyResponse();
		response.setPharmacyId(pharmacy.getPharmacyId());
		response.setName(pharmacy.getName());
		response.setGstNo(pharmacy.getGstNo());
		response.setLicenceNo(pharmacy.getLicenceNo());

		return response;
	}

}
