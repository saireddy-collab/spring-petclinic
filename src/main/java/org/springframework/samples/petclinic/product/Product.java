package org.springframework.samples.petclinic.product;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.samples.petclinic.model.BaseEntity;

/**
 * Represents a product available in the pet clinic's inventory.
 * This class extends BaseEntity to inherit the id property for JPA persistence.
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

	/**
	 * The name of the product.
	 * Must not be blank and have a size between 3 and 100 characters.
	 */
	@Column(name = "name")
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
	private String name;

	/**
	 * A brief description of the product.
	 * Must not be blank and have a size between 10 and 500 characters.
	 */
	@Column(name = "description")
	@NotBlank(message = "Description cannot be blank")
	@Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
	private String description;

	/**
	 * The price of the product.
	 * Must not be null and be a positive value.
	 */
	@Column(name = "price")
	@NotNull(message = "Price cannot be null")
	@DecimalMin(value = "0.01\