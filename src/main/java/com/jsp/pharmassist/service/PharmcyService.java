package com.jsp.pharmassist.service;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.mapper.PharmcyMapper;
import com.jsp.pharmassist.repository.PharmcyRepository;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;
import com.jsp.pharmassist.util.AppResponseBuilder;

import jakarta.validation.Valid;


@Service
public class PharmcyService {
	
	private final PharmcyRepository pharmcyRepository;
	private final AppResponseBuilder appResponseBuilder;
	private final PharmcyMapper mapper;
	
	public PharmcyService(PharmcyRepository pharmcyRepository, AppResponseBuilder appResponseBuilder,
			PharmcyMapper mapper) {
		super();
		this.pharmcyRepository = pharmcyRepository;
		this.appResponseBuilder = appResponseBuilder;
		this.mapper = mapper;
	}

	public PharmcyResponse addPharmcy(@Valid PharmacyRequest pharmcyRequest) {
		Pharmacy pharmacy = pharmcyRepository.save(mapper.mapToPharmcy(pharmcyRequest, new Pharmacy()));
		return mapper.mapToPharmcyResponse(pharmacy);
	}

	public PharmcyResponse findPharmacyById(String pharmacyId) {
		return pharmcyRepository.findById(pharmacyId)
				.map(mapper :: mapToPharmcyResponse)
				.orElseThrow(()-> new PharmacyNotFoundByIdException("Failed to find the Pharmacy"));
	}
	
	

}
