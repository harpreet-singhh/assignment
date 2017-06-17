package com.assignment.cart;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.assignment.cart.config.ApplicationConfig;
import com.assignment.cart.exception.ResourceNotFoundException;
import com.assignment.cart.model.Product;
import com.assignment.cart.model.ProductCategory;
import com.assignment.cart.model.ShoppingCart;
import com.assignment.cart.resource.ShoppingCartResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class ShoppingCartResourceTest {

	@Autowired
	ShoppingCartResource sResource;

	@Test
	public void testCreateCartOneItem() {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		ShoppingCart cart = sResource.createCart(product);
		Assert.assertNotNull(cart);
		Assert.assertEquals(product, cart.getItems().get(0));
		Assert.assertEquals(new Integer("1"), cart.getTotalItems());
		Assert.assertEquals(new Double("20"), cart.getTotalAmount());
		Assert.assertEquals(new Double("2"), cart.getTotalTax());
		Assert.assertEquals(new Double("22"), cart.getTotalAmountWithTax());
	}

	@Test
	public void testCreateCartTwoItem() {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category);

		ShoppingCart cart = sResource.createCart(product);
		ShoppingCart actualCart = sResource.updateCart(product2, cart.getCartId());

		Assert.assertNotNull(actualCart);
		Assert.assertEquals(new Integer("3"), actualCart.getTotalItems());
		Assert.assertEquals(new Double("420"), actualCart.getTotalAmount());
		Assert.assertEquals(new Double("42"), actualCart.getTotalTax());
		Assert.assertEquals(new Double("462"), cart.getTotalAmountWithTax());
	}
	
	@Test
	public void testCreateCartTwoItemDiffCategory() {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		ProductCategory category2 = new ProductCategory(1, "Category A", 0.20d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category2);

		ShoppingCart cart = sResource.createCart(product);
		ShoppingCart actualCart = sResource.updateCart(product2, cart.getCartId());

		Assert.assertNotNull(actualCart);
		Assert.assertEquals(new Integer("3"), actualCart.getTotalItems());
		Assert.assertEquals(new Double("420"), actualCart.getTotalAmount());
		Assert.assertEquals(new Double("82"), actualCart.getTotalTax());
		Assert.assertEquals(new Double("502"), cart.getTotalAmountWithTax());
	}
	
	@Test
	public void testCartNotFound() {
		try {
			sResource.getCart(100);
		} catch (ResourceNotFoundException e) {
			Assert.assertNotNull(e);
		}
	}

	@Test
	public void testCreateAndRemoveItem() {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category);

		ShoppingCart cart = sResource.createCart(product);
		// add item
		sResource.updateCart(product2, cart.getCartId());
		// remove the added item
		ShoppingCart actualCart = sResource.removeFromCart(product2, cart.getCartId());
		Assert.assertNotNull(actualCart);
		Assert.assertEquals(new Integer("1"), actualCart.getTotalItems());
		Assert.assertEquals(new Double("20"), actualCart.getTotalAmount());
		Assert.assertEquals(new Double("2"), actualCart.getTotalTax());
		Assert.assertEquals(new Double("22"), cart.getTotalAmountWithTax());
	}

	@Test
	public void testCreateAddAndPrintBill() {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category);
		ShoppingCart cart = sResource.createCart(product);
		// add item
		ShoppingCart actualCart = sResource.updateCart(product2, cart.getCartId());
		Assert.assertNotNull(actualCart);
		Assert.assertEquals(new Integer("3"), actualCart.getTotalItems());
		Assert.assertEquals(new Double("420"), actualCart.getTotalAmount());
		Assert.assertEquals(new Double("42"), actualCart.getTotalTax());
		Assert.assertEquals(new Double("462"), cart.getTotalAmountWithTax());
		System.out.println(actualCart.printBill().toString());
	}

}
