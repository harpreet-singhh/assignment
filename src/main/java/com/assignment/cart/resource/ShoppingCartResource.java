package com.assignment.cart.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.cart.model.Product;
import com.assignment.cart.model.ShoppingCart;
import com.assignment.cart.model.ShoppingCartBillDetails;
import com.assignment.cart.service.IShoppingCartService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {

	@Autowired
	IShoppingCartService shoppingCartService;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ShoppingCart createCart(@RequestBody Product product) {
		ShoppingCart cart = null;
		if (product != null) {
			cart = shoppingCartService.createCart(product);
		}
		return cart;
	}

	@RequestMapping(path = "/{cartId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ShoppingCart updateCart(@RequestBody Product product, @PathVariable("cartId") Integer cartId) {
		ShoppingCart cart = null;
		if (product != null) {
			cart = shoppingCartService.addOrUpdate(cartId, product);
		}
		return cart;
	}

	@RequestMapping(path = "/{cartId}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public ShoppingCart removeFromCart(@RequestBody Product product, @PathVariable("cartId") Integer cartId) {
		ShoppingCart cart = null;
		if (product != null) {
			cart = shoppingCartService.removeFromCart(cartId, product);
		}
		return cart;
	}

	@RequestMapping(path = "/{cartId}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ShoppingCart getCart(@PathVariable("cartId") Integer cartId) {
		return shoppingCartService.getCart(cartId);
	}

	@RequestMapping(path = "/{cartId}/checkout", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public ShoppingCartBillDetails checkoutCart(@PathVariable("cartId") Integer cartId) {
		ShoppingCart cart = shoppingCartService.getCart(cartId);
		System.out.println(cart.printBill());
		return new ShoppingCartBillDetails(cartId, cart.printBill());
	}

}
