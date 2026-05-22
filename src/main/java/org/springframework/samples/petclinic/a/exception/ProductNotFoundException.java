package org.springframework.samples.petclinic.a.exception;

import org.springframework.samples.petclinic.error.ErrorCode;
import org.springframework.samples.petclinic.exception.ApiException;

/**
 * Exception thrown when a requested product is not found.
 *
 * Purpose: Signals that an operation on a product failed because the product
 * with the given ID could not be located in the system.
 * How to use: Catch this exception in higher layers to handle specific product not found scenarios,
 * typically translating it into an appropriate HTTP response status (e.g., 404 Not Found).
 */
public class ProductNotFoundException extends ApiException {

    /**
     * Constructs a new ProductNotFoundException with a specific product ID.
     *
     * Purpose: Creates an exception instance indicating a product was not found.
     * How to use: Throw this exception from a service layer method when a product lookup fails.
     * @param id The ID of the product that was not found.
     */
    public ProductNotFoundException(Integer id) {
        super(ErrorCode.RESOURCE_NOT_FOUND, "Product not found with ID: " + id);
    }
}
