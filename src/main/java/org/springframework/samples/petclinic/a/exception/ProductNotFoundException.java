package org.springframework.samples.petclinic.a.exception;

import org.springframework.samples.petclinic.error.ErrorCode;
import org.springframework.samples.petclinic.exception.ApiException;

/**
 * Purpose: Represents an exception thrown when a requested product cannot be found.
 * How to use: Thrown by the service layer when a product lookup by ID fails to return a result.
 * This exception is caught by the global exception handler to return an appropriate HTTP 404 response.
 */
public class ProductNotFoundException extends ApiException {

	/**
	 * Purpose: Constructs a new ProductNotFoundException with a descriptive message including the product ID.
	 * How to use: Create an instance when a product is not found, typically in the service layer.
	 * @param productId The unique identifier of the product that was not found.
	 */
	public ProductNotFoundException(Long productId) {
		super(ErrorCode.RESOURCE_NOT_FOUND, "Product not found with id: " + productId);
	}

}