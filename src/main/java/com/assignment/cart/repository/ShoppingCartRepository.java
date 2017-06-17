package com.assignment.cart.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.assignment.cart.config.exception.ShoppingCartNotFoundException;
import com.assignment.cart.model.Product;
import com.assignment.cart.model.ShoppingCart;

@Component
public class ShoppingCartRepository {
	
	private static Map<Integer, ShoppingCart> carts = new HashMap<Integer, ShoppingCart>();
	
	public ShoppingCart get(Integer cartId){
		ShoppingCart cart = null;
		if(cartId == null){
			return cart;
		}
		for (Map.Entry<Integer, ShoppingCart> cartItem : carts.entrySet()) {
			if(cartItem.getKey().equals(cartId)){
				cart = cartItem.getValue();
				break;
			}
		}
		if(cart == null){
			throw new ShoppingCartNotFoundException("Cart no found.");
		}
		return cart;
	}
	
	public ShoppingCart delete(Integer cartId, Product product){
		ShoppingCart cart = get(cartId);
		if(cart != null){
			cart.removeProduct(product);
		}
		carts.put(cartId, cart);
		return cart;
	}
	
	public ShoppingCart persist(Product product){
		Integer pKey = carts.size() + 1;
		ShoppingCart cart = new ShoppingCart(pKey);
		cart.addProduct(product);
		carts.put(pKey, cart);
		return cart;
	}
	
	public ShoppingCart update(Integer cartId, Product product){
		ShoppingCart cart = get(cartId);
		if(cart != null){
			cart.addProduct(product);
		}
		carts.put(cartId, cart);
		return cart;
	}
}
