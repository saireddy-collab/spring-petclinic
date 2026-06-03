package org.springframework.samples.petclinic.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested product cannot be found.
 * This custom exception helps in handling specific domain-related 'not found' scenarios.
 * It is annotated with {@code @ResponseStatus(HttpStatus.NOT_FOUND)} to automatically
 * map to an HTTP 404 response when thrown from a Spring MVC controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	/**
	 * Constructs a new ProductNotFoundException with a default message indicating the missing product ID.
	 * @param productId The ID of the product that was not found.
	 */
	public ProductNotFoundException(Integer productId) {
		super("Product with ID " + productId + " not found");
	}

	/**
	 * Constructs a new ProductNotFoundException with a custom detail message.
	 * @param message The detail message for the exception.
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ProductNotFoundException with the specified detail message and cause.
	 * @param message The detail message for the exception.
	 * @param cause The cause of the exception.
	 */
	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new ProductNotFoundException with the specified cause.
	 * @param cause The cause of the exception.
	 */
	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}

}
