package com.jsp.pharmassist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jsp.pharmassist.config.GenerateCustomId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {

	@Id
	@GenerateCustomId
	private String cartItemId;
	private int quantity;
	private double price;
	private String medicineId;	

	@ManyToOne()
	@JsonBackReference
	private Cart cart;

//	@ManyToOne
//	private Medicine medicine;

	//	public CartItem(String cartId, String medicineName, int quantity, double price) {
	//        this.cartId = cartId;
	//        this.medicineName = medicineName;
	//        this.quantity = quantity;
	//        this.price = price;
	//    }

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public Medicine getMedicine() {
//		return medicine;
//	}
//
//	public void setMedicine(Medicine medicine) {
//		this.medicine = medicine;
//	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}



}
