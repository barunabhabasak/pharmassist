package com.jsp.pharmassist.responsedtos;

import java.time.LocalDateTime;
import java.util.List;

import com.jsp.pharmassist.entity.CartItem;

public class BillResponse {
	
	private String billId;
    private double totalAmount;
    private double gstInPercentage;
    private double totalPayableAmount;
    private LocalDateTime dateTime;
    private CartResponse cart;
    private List<CartItemResponse> cartItems;
	private PatientResponse patient;
    
	public List<CartItemResponse> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemResponse> cartItems) {
		this.cartItems = cartItems;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getGstInPercentage() {
		return gstInPercentage;
	}
	public void setGstInPercentage(double gstInPercentage) {
		this.gstInPercentage = gstInPercentage;
	}
	public double getTotalPayableAmount() {
		return totalPayableAmount;
	}
	public void setTotalPayableAmount(double totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public CartResponse getCart() {
		return cart;
	}
	public void setCart(CartResponse cart) {
		this.cart = cart;
	}
	public PatientResponse getPatient() {
		return patient;
	}
	public void setPatient(PatientResponse patient) {
		this.patient = patient;
	}
    
    
    

}
