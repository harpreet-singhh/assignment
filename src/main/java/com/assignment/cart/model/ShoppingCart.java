package com.assignment.cart.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {

	private Integer cartId;

	private List<Product> items;

	private Double totalAmount;

	private Double totalTax;

	private Double totalAmountWithTax;

	private Integer totalItems;

	public ShoppingCart() {
	}

	public void addProduct(Product product) {
		if (items != null && items.size() > 0) {
			Product productInCart = getProductFromCart(product);
			if (productInCart == null) {
				items.add(product);
			} else {
				productInCart.updateQuantity(product.getQuantity());
			}
		} else {
			items.add(product);
		}
		processCart();
	}

	public void removeProduct(Product product) {
		if (items != null && items.size() > 0) {
			for (Iterator<Product> iterator = items.iterator(); iterator.hasNext();) {
				Product item = (Product) iterator.next();
				if (item.equals(product)) {
					iterator.remove();
					break;
				}
			}
			processCart();
		}
	}

	private Product getProductFromCart(Product product) {
		if (items.size() == 0) {
			return null;
		}
		for (Product item : items) {
			if (item.equals(product)) {
				return item;
			}
		}
		return null;
	}

	public ShoppingCart(Integer id) {
		cartId = id;
		items = new ArrayList<Product>();
		totalItems = 0;
		totalAmount = 0d;
		totalTax = 0d;
		totalAmountWithTax = 0d;
	}

	private void processCart() {
		totalItems = 0;
		totalAmount = 0d;
		totalTax = 0d;
		totalAmountWithTax = 0d;
		for (Product product : items) {
			totalItems += product.getQuantity();
			totalAmount += product.getTotalPrice();
			totalTax += product.getTotalTax();
		}
		totalAmountWithTax = totalAmount + totalTax;
	}

	public String printBill() {
		StringBuilder bill = new StringBuilder();
		bill.append("-----------------Cart Details----------------------\n");
		bill.append("Total Items : " + totalItems + "\n");
		bill.append("Net Payable Amount : " + totalAmountWithTax + "\n");
		bill.append("Total Amount : " + totalAmount + "\n");
		bill.append("Total Tax : " + totalTax + "\n");
		for (Product product : items) {
			bill.append("----------------------------------------------------------------------------------------------------------\n");
			bill.append(
					"Product Id : " + product.getProductId() + " Product Name : " + product.getProductName());
			bill.append(" Quantity : " + product.getQuantity() + " Price : " + product.getPrice() + " Total Price :"
					+ product.getTotalPrice());
			bill.append(" Total Tax : " + product.getTotalTax() + "\n");
		}
		return bill.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public Double getTotalAmountWithTax() {
		return totalAmountWithTax;
	}

	public void setTotalAmountWithTax(Double totalAmountWithTax) {
		this.totalAmountWithTax = totalAmountWithTax;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

}