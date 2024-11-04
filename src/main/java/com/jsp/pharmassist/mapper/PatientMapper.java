package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;
import com.jsp.pharmassist.responsedtos.PharmcyResponse;

@Component
public class PatientMapper {
	
	public Patient mapToPatient(PatientRequest patientRequest, Patient patient) {
		patient.setName(patientRequest.getName());
		patient.setPhoneNo(patientRequest.getPhoneNo());
		patient.setEmail(patientRequest.getEmail());
		patient.setGender(patientRequest.getGender());
		patient.setDateOfBirth(patientRequest.getDateOfBirth());

		return patient;
	}

	public PatientResponse mapToPatientResponse(Patient patient) {
		
		PatientResponse response = new PatientResponse();
		response.setPatientId(patient.getPatientId());
		response.setName(patient.getName());
		response.setEmail(patient.getEmail());
		response.setGender(patient.getGender());
		response.setPhoneNo(patient.getPhoneNo());
		response.setDateOfBirth(patient.getDateOfBirth());
		return response;
	}

}
