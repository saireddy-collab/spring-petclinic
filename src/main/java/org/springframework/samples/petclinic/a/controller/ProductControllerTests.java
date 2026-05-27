package org.springframework.samples.petclinic.a.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.a.dto.ProductServiceAccept;
import org.springframework.samples.petclinic.a.dto.ProductServiceResponse;
import org.springframework.samples.petclinic.a.exception.ProductNotFoundException;
import org.springframework.samples.petclinic.a.service.ProductService;
import org.springframework.samples.petclinic.error.ErrorCode;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Purpose: Integration tests for the {@link ProductController}.
 * How to use: Uses MockMvc to simulate HTTP requests to the controller and asserts responses.
 * Mocks the {@link ProductService} to isolate controller logic from underlying service implementation.
 * Assumes the existence of a global exception handler and ApiResponse structure.
 */
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", roles = {"USER"})
public class ProductControllerTests {

	/**
	 * Purpose: MockMvc instance for performing HTTP requests.
	 * How to use: Injected to simulate client requests to the controller endpoints.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Purpose: ObjectMapper for converting objects to/from JSON.
	 * How to use: Injected to serialize request bodies and deserialize response content for assertions.
	 */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Purpose: Mocked instance of {@link ProductService}.
	 * How to use: Replaces the actual service bean to control its behavior during tests and isolate the controller.
	 */
	@MockBean
	private ProductService productService;

	private ProductServiceResponse testProductResponse;
	private ProductServiceAccept testProductAccept;

	/**
	 * Purpose: Sets up common test data before each test method.
	 * How to use: Initializes DTOs used across multiple tests to avoid repetition.
	 */
	@BeforeEach
	void setUp() {
		testProductResponse = new ProductServiceResponse(1L, "Test Product", "A product for testing", new BigDecimal("10.00"), true);
		testProductAccept = new ProductServiceAccept("Test Product", "A product for testing", new BigDecimal("10.00"), true);
	}

	/**
	 * Purpose: Tests the creation of a new product.
	 * How to use: Verifies that a POST request successfully creates a product and returns the correct ApiResponse.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */
	@Test
	void testCreateProduct() throws Exception {
		Mockito.when(productService.saveProduct(any(ProductServiceAccept.class))).thenReturn(testProductResponse);

		mockMvc.perform(post("/a/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testProductAccept)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.message").value("Product created successfully"))
			.andExpect(jsonPath("$.data.id").value(1L))
			.andExpect(jsonPath("$.data.name").value("Test Product"));

		Mockito.verify(productService, Mockito.times(1)).saveProduct(any(ProductServiceAccept.class));
	}

	/**
	 * Purpose: Tests retrieving a product by ID.
	 * How to use: Verifies that a GET request for a specific ID returns the correct product and ApiResponse.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */	
	@Test
	void testGetProductById() throws Exception {
		Mockito.when(productService.findProductById(1L)).thenReturn(testProductResponse);

		mockMvc.perform(get("/a/products/{productId}", 1L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.data.id").value(1L))
			.andExpect(jsonPath("$.data.name").value("Test Product"));

		Mockito.verify(productService, Mockito.times(1)).findProductById(1L);
	}

	/**
	 * Purpose: Tests retrieving all products.
	 * How to use: Verifies that a GET request to the base path returns a list of products within an ApiResponse.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */	
	@Test
	void testGetAllProducts() throws Exception {
		List<ProductServiceResponse> allProducts = Arrays.asList(testProductResponse, new ProductServiceResponse(2L, "Another Product", "Another test", new BigDecimal("20.00"), false));
		Mockito.when(productService.findAllProducts()).thenReturn(allProducts);

		mockMvc.perform(get("/a/products"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.data.length()").value(2))
			.andExpect(jsonPath("$.data[0].id").value(1L));

		Mockito.verify(productService, Mockito.times(1)).findAllProducts();
	}

	/**
	 * Purpose: Tests updating an existing product.
	 * How to use: Verifies that a PUT request with updated data correctly modifies a product and returns the updated ApiResponse.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */
	@Test
	void testUpdateProduct() throws Exception {
		ProductServiceAccept updatedAccept = new ProductServiceAccept("Updated Product", "Updated desc", new BigDecimal("15.00"), false);
		ProductServiceResponse updatedResponse = new ProductServiceResponse(1L, "Updated Product", "Updated desc", new BigDecimal("15.00"), false);

		Mockito.when(productService.updateProduct(eq(1L), any(ProductServiceAccept.class))).thenReturn(updatedResponse);

		mockMvc.perform(put("/a/products/{productId}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedAccept)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.message").value("Product updated successfully"))
			.andExpect(jsonPath("$.data.name").value("Updated Product"));

		Mockito.verify(productService, Mockito.times(1)).updateProduct(eq(1L), any(ProductServiceAccept.class));
	}

	/**
	 * Purpose: Tests deleting a product by ID.
	 * How to use: Verifies that a DELETE request for a specific ID results in a NO_CONTENT status and a success message.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */	
	@Test
	void testDeleteProduct() throws Exception {
		Mockito.doNothing().when(productService).deleteProduct(1L);

		mockMvc.perform(delete("/a/products/{productId}", 1L))
			.andExpect(status().isNoContent())
			.andExpect(jsonPath("$.success").value(true))
			.andExpect(jsonPath("$.message").value("Product deleted successfully"));

		Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
	}

	/**
	 * Purpose: Tests handling of ProductNotFoundException when retrieving a product.
	 * How to use: Ensures that if the service throws ProductNotFoundException, the controller returns a 404 with an appropriate error response.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */	
	@Test
	void testGetProductNotFound() throws Exception {
		Mockito.when(productService.findProductById(99L)).thenThrow(new ProductNotFoundException(99L));

		mockMvc.perform(get("/a/products/{productId}", 99L))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.success").value(false))
			.andExpect(jsonPath("$.errors[0].errorCode").value(ErrorCode.RESOURCE_NOT_FOUND.name()))
			.andExpect(jsonPath("$.errors[0].message").value("Product not found with id: 99"));

		Mockito.verify(productService, Mockito.times(1)).findProductById(99L);
	}

	/**
	 * Purpose: Tests input validation for product creation.
	 * How to use: Sends a POST request with invalid data and verifies a 400 Bad Request status with validation errors.
	 * @throws Exception if mockMvc performs an invalid operation.
	 */	
	@Test
	void testCreateProductInvalidInput() throws Exception {
		ProductServiceAccept invalidAccept = new ProductServiceAccept("", null, new BigDecimal("-5.00"), null);

		mockMvc.perform(post("/a/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidAccept)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.success").value(false))
			.andExpect(jsonPath("$.errors.length()").value(3))
			.andExpect(jsonPath("$.errors[?(@.field == 'name')].message").value("Product name is required"))
			.andExpect(jsonPath("$.errors[?(@.field == 'price')].message").value("Product price must be non-negative"))
			.andExpect(jsonPath("$.errors[?(@.field == 'available')].message").value("Product availability status is required"));

		Mockito.verify(productService, Mockito.never()).saveProduct(any(ProductServiceAccept.class));
	}

}