package com.jsp.pharmassist.service;


import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.exception.AdminNotFoundByIdException;
import com.jsp.pharmassist.mapper.AdminMapper;
import com.jsp.pharmassist.repository.AdminRepository;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	private final AppResponseBuilder appResponseBuilder;
	private final AdminMapper adminMapper;

	public AdminService(AdminRepository adminRepository, AppResponseBuilder appResponseBuilder,
			AdminMapper adminMapper) {
		super();
		this.adminRepository = adminRepository;
		this.appResponseBuilder = appResponseBuilder;
		this.adminMapper = adminMapper;
	}


	public AdminResponse addAdmin(AdminRequest userRequest) {
		Admin user = adminRepository.save(adminMapper.mapToAdmin(userRequest, new Admin()));
		return adminMapper.mapToAdminResponse(user);
	}

	public List<AdminResponse> findAllAdmins() {
		return adminRepository.findAll()
				.stream()
				.map(adminMapper::mapToAdminResponse)
				.toList();
	}


	public AdminResponse findAdminById(String adminId) {
		return adminRepository.findById(adminId)
				.map(adminMapper :: mapToAdminResponse)
				.orElseThrow(() -> new AdminNotFoundByIdException("Failed to find the user"));
	}

	public AdminResponse updateAdmin(AdminRequest adminRequest, String adminId) {

		return adminRepository.findById(adminId)
				  .map(exAdmin -> {
					  adminMapper.mapToAdmin(adminRequest, exAdmin);
					  return adminRepository.save(exAdmin);
				  })
				  .map(adminMapper :: mapToAdminResponse)
				  .orElseThrow(() -> new AdminNotFoundByIdException("Failed to Update the user"));
	}


	public AdminResponse deleteAdminById(String adminId) {

		return adminRepository.findById(adminId)
				.map(user ->{
					adminRepository.delete(user);
					return user;
				})
				.map(adminMapper :: mapToAdminResponse)
				.orElseThrow(() ->new AdminNotFoundByIdException("Failed to Delete the user"));
	}
}
