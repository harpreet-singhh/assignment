package com.assignment.cart.model;

public class ShoppingCartBillDetails {

	private Integer cartId;

	private String billDetails;

	public ShoppingCartBillDetails() {
	}

	public ShoppingCartBillDetails(Integer cartId, String billDetails) {
		super();
		this.cartId = cartId;
		this.billDetails = billDetails;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(String billDetails) {
		this.billDetails = billDetails;
	}

}
