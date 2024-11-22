package com.jsp.pharmassist.responsedtos;

import java.util.List;

public class CartResponse {
	
	private String cartId;
//    private List<CartItemResponse> cartItems;
    private double totalItemsPrice;
    
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
//	public List<CartItemResponse> getCartItems() {
//		return cartItems;
//	}
//	public void setCartItems(List<CartItemResponse> cartItems) {
//		this.cartItems = cartItems;
//	}
	public double getTotalItemsPrice() {
		return totalItemsPrice;
	}
	public void setTotalItemsPrice(double totalItemsPrice) {
		this.totalItemsPrice = totalItemsPrice;
	}
    
    

}
