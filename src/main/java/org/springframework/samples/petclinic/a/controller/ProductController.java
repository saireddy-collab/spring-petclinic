package org.springframework.samples.petclinic.a.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.a.dto.ProductServiceAccept;
import org.springframework.samples.petclinic.a.dto.ProductServiceResponse;
import org.springframework.samples.petclinic.a.service.ProductService;
import org.springframework.samples.petclinic.response.ApiResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Purpose: REST controller for managing product-related operations.
 * How to use: Provides HTTP endpoints for creating, retrieving, updating, and deleting products.
 * This controller delegates business logic to {@link ProductService} and returns standardized {@link ApiResponse}.
 */
@RestController
@RequestMapping("/a/products")
public class ProductController {

	/**
	 * Purpose: The service responsible for handling product business logic.
	 * How to use: Injected to perform CRUD operations on products.
	 */
	private final ProductService productService;

	/**
	 * Purpose: Constructs a new ProductController with the specified product service.
	 * How to use: Spring automatically injects the ProductService dependency.
	 * @param productService The service that handles product operations.
	 */
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Purpose: Creates a new product.
	 * How to use: Send a POST request to `/a/products` with a {@link ProductServiceAccept} JSON body.
	 * @param accept The DTO containing product creation details.
	 * @return An {@link ApiResponse} containing the created product's details.
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<ProductServiceResponse> createProduct(@Valid @RequestBody ProductServiceAccept accept) {
		ProductServiceResponse newProduct = productService.saveProduct(accept);
		return ApiResponse.ok("Product created successfully", newProduct);
	}

	/**
	 * Purpose: Retrieves a product by its unique ID.
	 * How to use: Send a GET request to `/a/products/{productId}`.
	 * @param productId The ID of the product to retrieve.
	 * @return An {@link ApiResponse} containing the product's details.
	 */
	@GetMapping("/{productId}")
	public ApiResponse<ProductServiceResponse> getProductById(@PathVariable("productId") Long productId) {
		ProductServiceResponse product = productService.findProductById(productId);
		return ApiResponse.ok(product);
	}

	/**
	 * Purpose: Retrieves all products.
	 * How to use: Send a GET request to `/a/products`.
	 * @return An {@link ApiResponse} containing a list of all products.
	 */
	@GetMapping
	public ApiResponse<List<ProductServiceResponse>> getAllProducts() {
		List<ProductServiceResponse> products = productService.findAllProducts();
		return ApiResponse.ok(products);
	}

	/**
	 * Purpose: Updates an existing product.
	 * How to use: Send a PUT request to `/a/products/{productId}` with an updated {@link ProductServiceAccept} JSON body.
	 * @param productId The ID of the product to update.
	 * @param accept The DTO containing updated product details.
	 * @return An {@link ApiResponse} containing the updated product's details.
	 */
	@PutMapping("/{productId}")
	public ApiResponse<ProductServiceResponse> updateProduct(
			@PathVariable("productId") Long productId,
			@Valid @RequestBody ProductServiceAccept accept
	) {
		ProductServiceResponse updatedProduct = productService.updateProduct(productId, accept);
		return ApiResponse.ok("Product updated successfully", updatedProduct);
	}

	/**
	 * Purpose: Deletes a product by its unique ID.
	 * How to use: Send a DELETE request to `/a/products/{productId}`.
	 * @param productId The ID of the product to delete.
	 * @return An {@link ApiResponse} indicating successful deletion.
	 */
	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ApiResponse<Void> deleteProduct(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		return ApiResponse.ok("Product deleted successfully");
	}

}