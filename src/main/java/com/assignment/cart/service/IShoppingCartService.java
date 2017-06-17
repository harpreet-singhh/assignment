package com.assignment.cart.service;

import com.assignment.cart.model.Product;
import com.assignment.cart.model.ShoppingCart;

public interface IShoppingCartService {
	
	public ShoppingCart createCart(Product product);
	
	public ShoppingCart getCart(Integer cartId);
	
	public ShoppingCart addOrUpdate(Integer cartId, Product product);
	
	public ShoppingCart removeFromCart(Integer cartId, Product product);

}
