package com.jsp.pharmassist.controller;


import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.service.AdminService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final AppResponseBuilder builder;
  

	public AdminController(AdminService adminService, AppResponseBuilder builder) {
		super();
		this.adminService = adminService;
		this.builder = builder;
	}

	
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody @Valid AdminRequest adminRequest) {
		AdminResponse response = adminService.addAdmin(adminRequest);
		return builder.success(HttpStatus.CREATED, "Admin created successfully", response);

	}
    
    @GetMapping(value = "/admins")
    public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
        List<AdminResponse> response = adminService.findAllAdmins();
        return builder.success(HttpStatus.FOUND, "Admins found",response );
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable String id) {
        return adminService.findById(id);
    }
    
//    @PutMapping("/{id}")
//    public Admin updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
//        admin.setAdminId(id);
//        return adminService.save(admin);
//    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteById(id);
    }
}
