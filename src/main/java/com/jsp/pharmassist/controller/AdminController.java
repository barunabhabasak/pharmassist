package com.jsp.pharmassist.controller;

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

    @GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdminById(@PathVariable String adminId) {
    	AdminResponse response = adminService.findAdminById(adminId);	
		return builder.success(HttpStatus.FOUND, "Found successfully", response);
	}
    
    @PutMapping("/admins/{adminId}")
   	public ResponseEntity<ResponseStructure<AdminResponse>> updateUser(@RequestBody AdminRequest userRequest,@PathVariable String adminId ){
       	AdminResponse response = adminService.updateAdmin(userRequest,adminId);
   		return builder.success(HttpStatus.OK, "User Upadted successfully", response);
   	}

    @DeleteMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> deleteAdminById(@PathVariable String adminId){
    	AdminResponse response = adminService.deleteAdminById(adminId);
		return builder.success(HttpStatus.OK, "Deleted successfully", response);

	}
}
