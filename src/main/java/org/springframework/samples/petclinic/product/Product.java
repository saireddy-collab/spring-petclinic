package org.springframework.samples.petclinic.product;

import org.springframework.samples.petclinic.model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a product available in the pet clinic system. Extends {@link NamedEntity} to inherit the ID and name fields.
 * This entity captures details like description and price for each product.
 */
@Entity
@Table(name = "products")
public class Product extends NamedEntity {

	/**
	 * The detailed description of the product.
	 * This field must not be blank and its length must be between 3 and 255 characters.
	 */
	@Column(name = "description")
	@NotBlank(message = "Description cannot be blank")
	private String description;

	/**
	 * The price of the product.
	 * This field must not be null, must be greater than or equal to 0.01,
	 * and must have at most two fractional digits.
	 */
	@Column(name = "price")
	@NotNull(message = "Price cannot be null")
	@DecimalMin(value = "0.01", message = "Price must be at least 0.01")
	@Digits(integer = 10, fractional = 2, message = "Price must have at most two decimal places")
	private BigDecimal price;

	/**
	 * Default constructor for Product.
	 * Used by JPA for entity instantiation.
	 */
	public Product() {
	}

	/**
	 * Gets the description of the product.
	 * @return The product's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the product.
	 * @param description The new description for the product.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price of the product.
	 * @return The product's price.
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 * @param price The new price for the product.
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * Overrides the equals method from NamedEntity to include product-specific fields.
	 * @param o The reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Product product = (Product) o;
		return Objects.equals(description, product.description) && Objects.equals(price, product.price);
	}

	/**
	 * Returns a hash code value for the object.
	 * Overrides the hashCode method from NamedEntity to include product-specific fields.
	 * @return A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), description, price);
	}

	/**
	 * Returns a string representation of the object.
	 * @return A string representation of the Product, including its name, description, and price.
	 */
	@Override
	public String toString() {
		return "Product{" +
			   "id=" + getId() +
			   ", name='" + getName() + "'" +
			   ", description='" + description + "'" +
			   ", price=" + price +
			   '}';
	}
}
