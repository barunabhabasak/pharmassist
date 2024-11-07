package com.jsp.pharmassist.entity;

import java.time.LocalDate;

import com.jsp.pharmassist.config.GenerateCustomId;
import com.jsp.pharmassist.enums.Form;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Medicine {

	@Id
	@GenerateCustomId
	private String medicineId;
	
	@NotNull(message = "name can not be null")
	@NotBlank(message = "name can not be blank")
	private String name;
	
	@NotNull(message = "category can not be null")
	private String category;
	
	@NotNull(message = "ingredients can not be null")
	private String ingredients;
	
	@Min(value = 1, message = "dosageInMg cannot be negative")
	private int dosageInMg;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "form can not be null")
	private Form form;
	
	private String manufacturar;
	
	@Min(value = 1, message = "stockQuantity cannot be negative")
	private int stockQuantity;
	
	@NotNull(message = "expiryDate can not be null")
	private LocalDate expiryDate;
	
	@DecimalMin(value = "0.01", message = "price must be greater than 0")
	private Double price;

	
	@ManyToOne
	private Pharmacy pharmacy;
	
	public Pharmacy getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public int getDosageInMg() {
		return dosageInMg;
	}
	public void setDosageInMg(int dosageInMg) {
		this.dosageInMg = dosageInMg;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public String getManufacturar() {
		return manufacturar;
	}
	public void setManufacturar(String manufacturar) {
		this.manufacturar = manufacturar;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}




}
