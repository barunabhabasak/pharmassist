package com.jsp.pharmassist.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PharmacyRequest {
	
	@NotNull(message = "name can not be null")
	@NotBlank(message = "name can not be blank")
	@Pattern(regexp = "^[A-Za-z\\s.-]{2,50}$",
	    message = "Name must contain at least 1 uppercase, 1 lowercase, and have a length between 2 to 50 characters.")
	private String name;
	
	@NotNull(message = "gstNo can not be null")
	@NotBlank(message = "gstNo can not be blank")
	@Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
	    message = "The GST number in India is typically a 15-character alphanumeric string")
	private String gstNo;
	
	@NotNull(message = "licenceNo can not be null")
	@NotBlank(message = "licenceNo	 can not be blank")
	@Pattern(regexp = "^[A-Z]{2}-\\d{6}-[A-Z]{4}$",
	    message = "The lience number in India is typically a 6-character alphanumeric string")
	private String licenceNo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	

}
