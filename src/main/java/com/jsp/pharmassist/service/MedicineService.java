package com.jsp.pharmassist.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.pharmassist.entity.Medicine;
import com.jsp.pharmassist.entity.Pharmacy;
import com.jsp.pharmassist.enums.Form;
import com.jsp.pharmassist.exception.InvalidDataException;
import com.jsp.pharmassist.exception.InvalidDateFormatException;
import com.jsp.pharmassist.exception.InvalidFileFormatException;
import com.jsp.pharmassist.exception.NoMedicineFoundException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.mapper.MedicineMapper;
import com.jsp.pharmassist.repository.MedicineRepository;
import com.jsp.pharmassist.repository.PharmcyRepository;
import com.jsp.pharmassist.responsedtos.MedicineResponse;

import jakarta.validation.Valid;

@Service
public class MedicineService {

	private final MedicineRepository medicineRepository;
	private final PharmcyRepository pharmcyRepository;
	private final MedicineMapper medicineMapper;

	public MedicineService(MedicineRepository medicineRepository,PharmcyRepository pharmcyRepository
			,MedicineMapper medicineMapper) {
		super();
		this.medicineRepository = medicineRepository;
		this.pharmcyRepository = pharmcyRepository;
		this.medicineMapper = medicineMapper;
	}


	@org.springframework.transaction.annotation.Transactional
	public String uploadMedicines(MultipartFile file,String pharmacyId) {

		Pharmacy pharmacy = pharmcyRepository.findById(pharmacyId)
				.orElseThrow(() -> new PharmacyNotFoundByIdException("Pharmacy with ID " + pharmacyId + " not found"));
		try(XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {

			for(Sheet sheet : workbook) {
				for(Row row : sheet) {
					if(row.getRowNum()!=0)
					{
						Medicine medicine = new Medicine();

						getMedicine(medicine,row);

						medicine.setPharmacy(pharmacy);

						saveMedicine(medicine);

						pharmacy.getMedicines().add(medicine);										
					}
				}
			}

		}catch ( NotOfficeXmlFileException | IOException e) {
			throw new InvalidFileFormatException("Invalid file format");
		}

		return "Medicines Added";
	}

	public void getMedicine(Medicine medicine, Row row) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		try {
			medicine.setName(row.getCell(0).getStringCellValue());
			medicine.setCategory(row.getCell(1).getStringCellValue());
			medicine.setDosageInMg((int) row.getCell(2).getNumericCellValue());
			medicine.setForm(Form.valueOf(row.getCell(3).getStringCellValue()));
			medicine.setIngredients(row.getCell(4).getStringCellValue());
			medicine.setManufacturar(row.getCell(5).getStringCellValue());
			medicine.setPrice(row.getCell(6).getNumericCellValue());
			medicine.setExpiryDate(LocalDate.parse(row.getCell(7).getStringCellValue(), formatter));
			medicine.setStockQuantity((int) row.getCell(8).getNumericCellValue());
		}
		catch(DateTimeParseException ex) {
			throw new InvalidDateFormatException("Invalid date format in row " + row.getRowNum());
		}
		catch(IllegalStateException e) {
			throw new InvalidDataException("Data is in invalid format in row "+row.getRowNum());
		}				
	}


	public void saveMedicine(@Valid Medicine medicine) {

		medicineRepository.save(medicine); 

	}


	public List<MedicineResponse> findMedicineByNameOrIngredients(String text) {

		text = "%" +text+"%";
		List<Medicine> medicines = medicineRepository.findByNameLikeIgnoreCaseOrIngredientsLikeIgnoreCase(text, text);
		if(medicines.isEmpty())
			throw new NoMedicineFoundException("No medicine found");
		else
			return	medicines.stream()
					.map(medicineMapper :: mapToMedicineResponse)
					.toList();
	}



}
