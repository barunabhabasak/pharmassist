package com.jsp.pharmassist.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AdminRequest {
	
	@NotNull(message = "password can not be null")
	@NotBlank(message = "password can not be blank")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", 
	    message = "Password must contain at least 1 uppercase, 1 lowercase, 1 special character, 1 number, and have a length between 8 to 12 characters.")
	private String password;

	@NotNull(message = "email can not be null")
	@NotBlank(message = "email can not be blank")
	@Pattern(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "Invalid Gmail address")
	private String email;

	@NotNull(message = "phoneNo can not be null")
	@NotBlank(message = "phoneNo can not be blank")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Phone Number")
	private String phoneNo;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
