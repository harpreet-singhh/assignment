package com.assignment.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.cart.model.Product;
import com.assignment.cart.model.ShoppingCart;
import com.assignment.cart.repository.ShoppingCartRepository;
import com.assignment.cart.service.IShoppingCartService;

@Service
public class ShoppingCartService implements IShoppingCartService {

	@Autowired
	private ShoppingCartRepository repository;

	@Override
	public ShoppingCart createCart(Product product) {
		return repository.persist(product);
	}
	
	@Override
	public ShoppingCart getCart(Integer cartId) {
		return repository.get(cartId);
	}

	@Override
	public ShoppingCart removeFromCart(Integer cartId, Product product) {
		return repository.delete(cartId, product);
	}
	
	@Override
	public ShoppingCart addOrUpdate(Integer cartId, Product product) {
		return repository.update(cartId, product);
	}

	

}
