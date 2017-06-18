package com.assignment.cart;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.assignment.cart.config.ApplicationConfig;
import com.assignment.cart.model.Product;
import com.assignment.cart.model.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class ShoppingCartMockMvcTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateCart() throws Exception {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(product);
		mockMvc.perform(post("/cart").contentType("application/json").accept("application/json").content(body))
				.andExpect(status().isOk()).andExpect(jsonPath("$.items", hasSize(1)));
	}

	@Test
	public void testCartNotFound() throws Exception {
		mockMvc.perform(get("/cart/123").contentType("application/json").accept("application/json"))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code", is("NOT_FOUND")));
	}

	@Test
	public void testCreateCartTwoItem() throws Exception {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category);

		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/cart").contentType("application/json").accept("application/json")
				.content(mapper.writeValueAsString(product))).andExpect(status().isOk())
				.andExpect(jsonPath("$.items", hasSize(1)));

		mockMvc.perform(put("/cart/1").contentType("application/json").accept("application/json")
				.content(mapper.writeValueAsString(product2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.items", hasSize(2)));
	}

	@Test
	public void testCreateAndRemoveItem() throws Exception {
		ProductCategory category = new ProductCategory(1, "Category A", 0.10d);
		Product product = new Product(1, "Product A", 1, 20d, category);
		Product product2 = new Product(2, "Product B", 2, 200d, category);

		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(post("/cart").contentType("application/json").accept("application/json")
				.content(mapper.writeValueAsString(product))).andExpect(status().isOk())
				.andExpect(jsonPath("$.items", hasSize(1)));

		mockMvc.perform(put("/cart/1").contentType("application/json").accept("application/json")
				.content(mapper.writeValueAsString(product2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.items", hasSize(2)));

		mockMvc.perform(delete("/cart/1").contentType("application/json").accept("application/json")
				.content(mapper.writeValueAsString(product2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.items", hasSize(1)));
	}

}