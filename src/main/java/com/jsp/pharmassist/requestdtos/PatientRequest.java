package com.jsp.pharmassist.requestdtos;

import java.time.LocalDate;

import com.jsp.pharmassist.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class PatientRequest {

	@NotNull(message = "name can not be null")
	@NotBlank(message = "name can not be blank")
	@Pattern(regexp = "^[A-Za-z\\s.-]{2,50}$",
	message = "Name must contain at least 1 uppercase, 1 lowercase, and have a length between 2 to 50 characters.")
	private String name;

	@NotNull(message = "phoneNo can not be null")
	@NotBlank(message = "phoneNo can not be blank")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Phone Number")
	private String phoneNo;

	@NotNull(message = "email can not be null")
	@NotBlank(message = "email can not be blank")
	@Pattern(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "Invalid Gmail address")
	private String email;

	@NotNull(message = "gender cannot be null")
	private Gender gender;

	@NotNull(message = "dateOfBirth cannot be null")
	@Past(message = "dateOfBirth must be in the past")
	private LocalDate dateOfBirth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
	
}
