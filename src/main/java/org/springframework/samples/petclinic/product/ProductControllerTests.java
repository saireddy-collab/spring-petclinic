package org.springframework.samples.petclinic.product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for {@link ProductController}.
 * Uses MockMvc to simulate HTTP requests and verifies controller behavior.
 */
@WebMvcTest(ProductController.class)
class ProductControllerTests {

	private static final int TEST_PRODUCT_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	private Product product;

	/**
	 * Sets up test data before each test method.
	 */	
	@BeforeEach
	void setup() {
		product = new Product();
		product.setId(TEST_PRODUCT_ID);
		product.setName("Pet Food");
		product.setDescription("High-quality food for pets");
		product.setPrice(new BigDecimal("25.50"));
		product.setStock(100);
		
		given(this.productService.findProductById(TEST_PRODUCT_ID)).willReturn(product);
		
		Collection<Product> products = Arrays.asList(product, new Product("Pet Toy\