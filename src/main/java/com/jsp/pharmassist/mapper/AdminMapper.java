package com.jsp.pharmassist.mapper;

import com.jsp.pharmassist.entity.Admin;
import com.jsp.pharmassist.requestdtos.AdminRequest;
import com.jsp.pharmassist.responsedtos.AdminResponse;

public class AdminMapper {
	public Admin mapToUser(AdminRequest adminRequest, Admin admin) {
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(adminRequest.getPassword());
		admin.setPhoneNo(adminRequest.getPhoneNo());

		return admin;
	}

	public AdminResponse mapToUserResponse(Admin admin) {
		AdminResponse response = new AdminResponse();
		response.setAdminId(admin.getAdminId());
		response.setEmail(admin.getEmail());

		return response;
	}

}
