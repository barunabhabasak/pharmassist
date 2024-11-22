package com.jsp.pharmassist.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jsp.pharmassist.config.GenerateCustomId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GenerateCustomId
	private String cartId;

	@OneToMany(mappedBy = "cart")
	@JsonManagedReference
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	private double totalItemsPrice;

	private String patientId;


	public Cart(String patientId) {
		this.patientId = patientId;
	}

	public Cart() {
	}

	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public double getTotalItemsPrice() {
		return totalItemsPrice;
	}
	public void setTotalItemsPrice(double totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}


}
