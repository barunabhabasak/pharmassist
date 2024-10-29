package com.jsp.pharmassist.service;

import com.jsp.pharmassist.entity.Admin;
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
		Admin user = adminRepository.save(adminMapper.mapToUser(userRequest, new Admin()));
		return adminMapper.mapToUserResponse(user);
	}
	
	public List<AdminResponse> findAllAdmins() {
		return adminRepository.findAll()
							  .stream()
							  .map(adminMapper::mapToUserResponse)
							  .toList();
	}

	
	public Optional<Admin> findById(String id) {
		return adminRepository.findById(id);
	}

	public void deleteById(String id) {
		adminRepository.deleteById(id);
	}
}
