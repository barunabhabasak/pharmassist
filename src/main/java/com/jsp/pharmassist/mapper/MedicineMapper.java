package com.jsp.pharmassist.mapper;

import org.springframework.stereotype.Component;

import com.jsp.pharmassist.entity.Medicine;
import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.requestdtos.MedicineRequest;
import com.jsp.pharmassist.responsedtos.MedicineResponse;
import com.jsp.pharmassist.responsedtos.PatientResponse;

@Component
public class MedicineMapper {

	public Medicine mapToMedicine(MedicineRequest medicineRequest, Medicine medicine) {
		medicine.setName(medicineRequest.getName());
		medicine.setCategory(medicineRequest.getCategory());
		medicine.setDosageInMg(medicineRequest.getDosageInMg());
		medicine.setExpiryDate(medicineRequest.getExpiryDate());
		medicine.setForm(medicineRequest.getForm());
		medicine.setIngredients(medicineRequest.getIngredients());
		medicine.setManufacturar(medicineRequest.getManufacturar());
		medicine.setPrice(medicineRequest.getPrice());
		medicine.setStockQuantity(medicineRequest.getStockQuantity());

		return medicine;
	}

	public MedicineResponse mapToMedicineResponse(Medicine medicine) {

		MedicineResponse response = new MedicineResponse();

		response.setMedicineId(medicine.getMedicineId());
		response.setName(medicine.getName());
		response.setCategory(medicine.getCategory());
		response.setForm(medicine.getForm());
		response.setDosageInMg(medicine.getDosageInMg());
		response.setIngredients(medicine.getIngredients());
		response.setManufacturar(medicine.getManufacturar());
		response.setPrice(medicine.getPrice());
		response.setStockQuantity(medicine.getStockQuantity());
		response.setExpiryDate(medicine.getExpiryDate());

		return response;
	}

}
