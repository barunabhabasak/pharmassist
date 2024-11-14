package com.jsp.pharmassist.controller;

import com.jsp.pharmassist.util.ErrorStructure;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;
import com.jsp.pharmassist.service.AdminService;
import com.jsp.pharmassist.util.AppResponseBuilder;
import com.jsp.pharmassist.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	@Operation(description = "The endpoint can be used to add the admin",
			responses = {
					@ApiResponse(responseCode = "201", description = "Admin Added successfully"),
					@ApiResponse(responseCode = "404", description = "Failed to Add",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody @Valid AdminRequest adminRequest) {
		AdminResponse response = adminService.addAdmin(adminRequest);
		return builder.success(HttpStatus.CREATED, "Admin created successfully", response);

	}

	@Operation(description = "The endpoint can be used to findall the admin",
			responses = {
					@ApiResponse(responseCode = "302", description = "Admin Found successfully"),
					@ApiResponse(responseCode = "404", description = "No admin Found",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping(value = "/admins")
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmins() {
		List<AdminResponse> response = adminService.findAllAdmins();
		return builder.success(HttpStatus.FOUND, "Admins found",response );
	}

	@Operation(description = "The endpoint can be used to find the admin based on the the Unique ID",
			responses = {
					@ApiResponse(responseCode = "302", description = "Admin Found successfully"),
					@ApiResponse(responseCode = "404", description = "Admin id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findAdminById(@PathVariable String adminId) {
		AdminResponse response = adminService.findAdminById(adminId);	
		return builder.success(HttpStatus.FOUND, "Found successfully", response);
	}

	@Operation(description = "The endpoint can be used to update the admin based on the the Unique ID and admin request",
			responses = {
					@ApiResponse(responseCode = "200", description = "Admin Updated successfully"),
					@ApiResponse(responseCode = "404", description = "Admin id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					}),
					@ApiResponse(responseCode = "400", description = "Bad Request",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateUser(@RequestBody AdminRequest userRequest,@PathVariable String adminId ){
		AdminResponse response = adminService.updateAdmin(userRequest,adminId);
		return builder.success(HttpStatus.OK, "User Upadted successfully", response);
 	}

	
	@Operation(description = "The endpoint can be used to delete the admin based on the the Unique ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Admin Deleted successfully"),
					@ApiResponse(responseCode = "404", description = "Admin id not found by ID",
					content = {
							@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@DeleteMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> deleteAdminById(@PathVariable String adminId){
		AdminResponse response = adminService.deleteAdminById(adminId);
		return builder.success(HttpStatus.OK, "Deleted successfully", response);

	}
}
