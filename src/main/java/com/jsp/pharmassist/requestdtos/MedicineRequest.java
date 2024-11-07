package com.jsp.pharmassist.requestdtos;

import java.time.LocalDate;

import com.jsp.pharmassist.enums.Form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MedicineRequest {
	
	@NotNull(message = "name can not be null")
	@NotBlank(message = "name can not be blank")
	@Pattern(regexp = "^[A-Za-z\\s.-]{2,50}$",
	message = "Name must contain at least 1 uppercase, 1 lowercase, and have a length between 2 to 50 characters.")
	private String name;
	
	@NotNull(message = "category can not be null")
	@NotBlank(message = "category can not be blank")
	private String category;
	
	@NotNull(message = "ingredients can not be null")
	@NotBlank(message = "ingredients can not be blank")
	private String ingredients;
	
	@NotNull(message = "dosageInMg can not be null")
	@NotBlank(message = "dosageInMg can not be blank")
	@Min(value = 1, message = "dosageInMg cannot be negative")
	private int dosageInMg;
	
	@NotNull(message = "form can not be null")
	@NotBlank(message = "form can not be blank")
	private Form form;
	
	@NotNull(message = "manufacturar can not be null")
	@NotBlank(message = "manufacturar can not be blank")
	private String manufacturar;
	
	@NotNull(message = "name can not be null")
	@NotBlank(message = "name can not be blank")
	@Min(value = 0, message = "stockQuantity cannot be negative")
	private int stockQuantity;
	
	@NotNull(message = "expiryDate can not be null")
	private LocalDate expiryDate;
	
	@NotNull(message = "price can not be null")
	@NotBlank(message = "price can not be blank")
	@DecimalMin(value = "0.01", message = "price must be greater than 0")
	private Double price;
	
	
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
