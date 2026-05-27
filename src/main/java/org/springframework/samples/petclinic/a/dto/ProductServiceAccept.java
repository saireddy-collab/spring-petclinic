package org.springframework.samples.petclinic.a.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Purpose: Data Transfer Object (DTO) for accepting product creation or update requests.
 * How to use: Used as a request body in controller methods for `POST` and `PUT` operations.
 * Contains validation annotations to ensure incoming data meets business constraints.
 */
public class ProductServiceAccept {

	/**
	 * Purpose: The name of the product.
	 * How to use: Must not be blank. Represents the primary identifier for a product in terms of user-facing data.
	 */
	@NotBlank(message = "Product name is required")
	private String name;

	/**
	 * Purpose: A detailed description of the product.
	 * How to use: Optional text providing more context about the product.
	 */
	private String description;

	/**
	 * Purpose: The price of the product.
	 * How to use: Must be a non-negative decimal value. Essential for pricing products.
	 */
	@NotNull(message = "Product price is required")
	@DecimalMin(value = "0.0", inclusive = true, message = "Product price must be non-negative")
	private BigDecimal price;

	/**
	 * Purpose: Indicates whether the product is available for purchase.
	 * How to use: Must not be null. Controls the visibility and purchasability of the product.
	 */
	@NotNull(message = "Product availability status is required")
	private Boolean available;

	/**
	 * Purpose: Default constructor.
	 * How to use: Used by frameworks like Spring for object instantiation during deserialization.
	 */
	public ProductServiceAccept() {
	}

	/**
	 * Purpose: Constructs a new ProductServiceAccept with all required fields.
	 * How to use: Used for programmatically creating DTO instances, often in tests or utility classes.
	 * @param name The name of the product.
	 * @param description The description of the product.
	 * @param price The price of the product.
	 * @param available The availability status of the product.
	 */
	public ProductServiceAccept(String name, String description, BigDecimal price, Boolean available) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	/**
	 * Purpose: Retrieves the name of the product.
	 * How to use: Accesses the product's name from the DTO.
	 * @return The product name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Purpose: Sets the name of the product.
	 * How to use: Updates the product's name within the DTO.
	 * @param name The new product name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Purpose: Retrieves the description of the product.
	 * How to use: Accesses the product's description from the DTO.
	 * @return The product description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Purpose: Sets the description of the product.
	 * How to use: Updates the product's description within the DTO.
	 * @param description The new product description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Purpose: Retrieves the price of the product.
	 * How to use: Accesses the product's price from the DTO.
	 * @return The product price.
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Purpose: Sets the price of the product.
	 * How to use: Updates the product's price within the DTO.
	 * @param price The new product price.
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Purpose: Retrieves the availability status of the product.
	 * How to use: Accesses the product's availability from the DTO.
	 * @return The availability status.
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * Purpose: Sets the availability status of the product.
	 * How to use: Updates the product's availability within the DTO.
	 * @param available The new availability status.
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

}