package org.springframework.samples.petclinic.a.service;

import org.springframework.samples.petclinic.a.dto.ProductServiceAccept;
import org.springframework.samples.petclinic.a.dto.ProductServiceResponse;

import java.util.List;

/**
 * Purpose: Defines the contract for product management operations.
 * How to use: Provides an abstraction layer for business logic related to products,
 * allowing controllers to interact with product data without knowing persistence details.
 */
public interface ProductService {

	/**
	 * Purpose: Saves a new product or updates an existing one if the ID is provided in the DTO.
	 * How to use: Call this method to persist product data from a client request.
	 * @param accept The {@link ProductServiceAccept} DTO containing product data.
	 * @return The {@link ProductServiceResponse} DTO of the saved/updated product.
	 */
	ProductServiceResponse saveProduct(ProductServiceAccept accept);

	/**
	 * Purpose: Updates an existing product identified by its ID.
	 * How to use: Call this method to modify an existing product's details.
	 * @param productId The unique identifier of the product to update.
	 * @param accept The {@link ProductServiceAccept} DTO with updated product data.
	 * @return The {@link ProductServiceResponse} DTO of the updated product.
	 * @throws org.springframework.samples.petclinic.a.exception.ProductNotFoundException if the product with the given ID does not exist.
	 */
	ProductServiceResponse updateProduct(Long productId, ProductServiceAccept accept);

	/**
	 * Purpose: Retrieves a product by its unique identifier.
	 * How to use: Call this method to fetch details of a specific product.
	 * @param productId The unique identifier of the product to retrieve.
	 * @return The {@link ProductServiceResponse} DTO of the found product.
	 * @throws org.springframework.samples.petclinic.a.exception.ProductNotFoundException if the product with the given ID does not exist.
	 */
	ProductServiceResponse findProductById(Long productId);

	/**
	 * Purpose: Retrieves a list of all products.
	 * How to use: Call this method to get an overview of all available products.
	 * @return A list of {@link ProductServiceResponse} DTOs representing all products.
	 */
	List<ProductServiceResponse> findAllProducts();

	/**
	 * Purpose: Deletes a product by its unique identifier.
	 * How to use: Call this method to remove a product from the system.
	 * @param productId The unique identifier of the product to delete.
	 * @throws org.springframework.samples.petclinic.a.exception.ProductNotFoundException if the product with the given ID does not exist.
	 */
	void deleteProduct(Long productId);

}