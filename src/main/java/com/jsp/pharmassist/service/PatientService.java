package com.jsp.pharmassist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.mapper.PatientMapper;
import com.jsp.pharmassist.repository.PatientRespository;
import com.jsp.pharmassist.repository.PharmcyRepository;
import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;

import jakarta.validation.Valid;

@Service
public class PatientService {

	private final PatientRespository patientRespository;
	private final PharmcyRepository pharmcyRepository;
	private final PatientMapper patientMapper;

	public PatientService(PatientRespository patientRespository, PharmcyRepository pharmcyRepository,
			PatientMapper patientMapper) {
		super();
		this.patientRespository = patientRespository;
		this.pharmcyRepository = pharmcyRepository;
		this.patientMapper = patientMapper;
	}


	public PatientResponse addPatient(@Valid PatientRequest patientRequest, String pharmacyId) {

		return pharmcyRepository.findById(pharmacyId)
				.map(pharmacy->{
					Patient patient = patientMapper.mapToPatient(patientRequest, new Patient());
					patient.setPharmacy(pharmacy);
					patient = patientRespository.save(patient);
					return patientMapper.mapToPatientResponse(patient);
				})
				.orElseThrow(()-> new PharmacyNotFoundByIdException("Pharmacy not found with ID: " + pharmacyId));
	}


	public List<PatientResponse> findAllPatientsByPharmacyId(String pharmacyId) {
		pharmcyRepository.findById(pharmacyId)
		.orElseThrow(()-> new PharmacyNotFoundByIdException("Pharmacy not found with ID: " + pharmacyId));

		return patientRespository.findAllByPharmacy_PharmacyId(pharmacyId)
				.stream()
				.map(patientMapper :: mapToPatientResponse)
				.toList();
	}
}
