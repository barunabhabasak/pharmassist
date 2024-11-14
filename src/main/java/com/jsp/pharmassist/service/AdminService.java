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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	private final AdminMapper adminMapper;
	private final PasswordEncoder encoder;

	public AdminService(AdminRepository adminRepository,AdminMapper adminMapper,PasswordEncoder encoder) {
		super();
		this.adminRepository = adminRepository;
		this.adminMapper = adminMapper;
		this.encoder = encoder;
	}


	public AdminResponse addAdmin(AdminRequest adminRequest) {
		Admin admin =adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setPassword(encoder.encode(admin.getPassword()));
		adminRepository.save(admin);
		return adminMapper.mapToAdminResponse(admin);
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
				.orElseThrow(() -> new AdminNotFoundByIdException("Failed to find the admin"));
	}

	public AdminResponse updateAdmin(AdminRequest adminRequest, String adminId) {

		return adminRepository.findById(adminId)
				  .map(exAdmin -> {
					  adminMapper.mapToAdmin(adminRequest, exAdmin);
					  return adminRepository.save(exAdmin);
				  })
				  .map(adminMapper :: mapToAdminResponse)
				  .orElseThrow(() -> new AdminNotFoundByIdException("Failed to Update the admin"));
	}


	public AdminResponse deleteAdminById(String adminId) {

		return adminRepository.findById(adminId)
				.map(admin ->{
					adminRepository.delete(admin);
					return admin;
				})
				.map(adminMapper :: mapToAdminResponse)
				.orElseThrow(() ->new AdminNotFoundByIdException("Failed to Delete the admin"));
	}
}
