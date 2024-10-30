package com.jsp.pharmassist.service;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.mapper.PharmcyMapper;
import com.jsp.pharmassist.repository.AdminRepository;
import com.jsp.pharmassist.repository.PharmcyRepository;
import com.jsp.pharmassist.requestdtos.PharmacyRequest;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;
import com.jsp.pharmassist.util.AppResponseBuilder;

import jakarta.validation.Valid;


@Service
public class PharmcyService {

	private final PharmcyRepository pharmcyRepository;
	private final AdminRepository adminRepository;
	private final AppResponseBuilder appResponseBuilder;
	private final PharmcyMapper mapper;

	public PharmcyService(PharmcyRepository pharmcyRepository, AppResponseBuilder appResponseBuilder,
			PharmcyMapper mapper,AdminRepository adminRepository) {
		super();
		this.pharmcyRepository = pharmcyRepository;
		this.appResponseBuilder = appResponseBuilder;
		this.mapper = mapper;
		this.adminRepository = adminRepository;
	}

	public PharmcyResponse addPharmcy(@Valid PharmacyRequest pharmcyRequest) {
		//		Pharmacy pharmacy = pharmcyRepository.save(mapper.mapToPharmcy(pharmcyRequest, new Pharmacy()));

		Pharmacy pharmacy = mapper.mapToPharmcy(pharmcyRequest, new Pharmacy());

		// Check if the pharmacy ID exists in the Admin table
		Admin admin = adminRepository.findById(pharmacy.getPharmacyId())
				.orElseThrow(()-> new AdminNotFoundByIdException("Admin not found with ID: " + pharmacy.getPharmacyId()));

		// Save Pharmacy entity
		Pharmacy savePharmacy = pharmcyRepository.save(pharmacy);

		// Update Admin entity with the new Pharmacy
		admin.setPharmacy(savePharmacy);
		adminRepository.save(admin); // Save Admin with the associated Pharmacy

		return mapper.mapToPharmcyResponse(pharmacy);
	}

	public PharmcyResponse findPharmacyById(String pharmacyId) {	
		return pharmcyRepository.findById(pharmacyId)
				.map(mapper :: mapToPharmcyResponse)
				.orElseThrow(()-> new PharmacyNotFoundByIdException("Failed to find the Pharmacy"));
	}

}
