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
import com.jsp.pharmassist.security.AuthUtils;
import com.jsp.pharmassist.util.AppResponseBuilder;

import jakarta.validation.Valid;


@Service
public class PharmcyService {

	private final PharmcyRepository pharmcyRepository;
	private final AdminRepository adminRepository;
	private final AppResponseBuilder appResponseBuilder;
	private final PharmcyMapper mapper;
	private final AuthUtils authUtils;

	public PharmcyService(PharmcyRepository pharmcyRepository, AdminRepository adminRepository,
			AppResponseBuilder appResponseBuilder, PharmcyMapper mapper, AuthUtils authUtils) {
		super();
		this.pharmcyRepository = pharmcyRepository;
		this.adminRepository = adminRepository;
		this.appResponseBuilder = appResponseBuilder;
		this.mapper = mapper;
		this.authUtils = authUtils;
	}

	public PharmcyResponse addPharmcy(@Valid PharmacyRequest pharmcyRequest) {
		Admin admin = authUtils.getCurrentAdmin();

//		return	adminRepository.findById(adminId)
//				.map(admin ->{
					Pharmacy pharmacy = mapper.mapToPharmcy(pharmcyRequest,new Pharmacy());
					pharmacy = pharmcyRepository.save(pharmacy);
					admin.setPharmacy(pharmacy);
					adminRepository.save(admin);
					return mapper.mapToPharmcyResponse(pharmacy);
//				})
//				.orElseThrow(() -> new AdminNotFoundByIdException("Failed to find the admin"));
	}

	public PharmcyResponse finaPharmacyByAdminId(String adminId) {

		return adminRepository.findById(adminId)
				.map(admin ->{
					Pharmacy pharmacy = admin.getPharmacy();
					return mapper.mapToPharmcyResponse(pharmacy);
				})
				.orElseThrow(()-> new AdminNotFoundByIdException("Failed to find the phamacy because "
						+ "of admin is not prsent"));
	}

	public PharmcyResponse updatePharmacyById(PharmacyRequest pharmcyRequest, String pharmacyId) {
		return	pharmcyRepository.findById(pharmacyId)
				.map(exPharmacy ->{
					mapper.mapToPharmcy(pharmcyRequest, exPharmacy);
					return pharmcyRepository.save(exPharmacy);
				})
				.map(mapper :: mapToPharmcyResponse)
				.orElseThrow(()-> new PharmacyNotFoundByIdException("Failed to update the Pharmacy"));
	}



}
