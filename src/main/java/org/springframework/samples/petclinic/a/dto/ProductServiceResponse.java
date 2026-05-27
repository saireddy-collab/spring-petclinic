package org.springframework.samples.petclinic.a.dto;

import java.math.BigDecimal;

/**
 * Purpose: Data Transfer Object (DTO) for returning product details in responses.
 * How to use: Used by controller methods to encapsulate product data for client consumption.
 * This DTO typically includes the product ID and all relevant fields for display.
 */
public class ProductServiceResponse {

	/**
	 * Purpose: The unique identifier for the product.
	 * How to use: Allows clients to reference specific products.
	 */
	private Long id;

	/**
	 * Purpose: The name of the product.
	 * How to use: Displays the product's name to the client.
	 */
	private String name;

	/**
	 * Purpose: A detailed description of the product.
	 * How to use: Provides additional descriptive text about the product to the client.
	 */	
	private String description;

	/**
	 * Purpose: The price of the product.
	 * How to use: Shows the current price of the product to the client.
	 */
	private BigDecimal price;

	/**
	 * Purpose: Indicates whether the product is currently available.
	 * How to use: Informs the client about the product's availability status.
	 */
	private Boolean available;

	/**
	 * Purpose: Default constructor.
	 * How to use: Used by frameworks like Spring for object instantiation during serialization.
	 */
	public ProductServiceResponse() {
	}

	/**
	 * Purpose: Constructs a new ProductServiceResponse with all product details.
	 * How to use: Used for mapping from an entity to a response DTO before sending to the client.
	 * @param id The unique identifier of the product.
	 * @param name The name of the product.
	 * @param description A detailed description of the product.
	 * @param price The price of the product.
	 * @param available Indicates if the product is available.
	 */
	public ProductServiceResponse(Long id, String name, String description, BigDecimal price, Boolean available) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
	}

	/**
	 * Purpose: Retrieves the unique identifier of the product.
	 * How to use: Accesses the product's ID from the DTO.
	 * @return The product ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Purpose: Sets the unique identifier of the product.
	 * How to use: Updates the product's ID within the DTO.
	 * @param id The new product ID.
	 */
	public void setId(Long id) {
		this.id = id;
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