package org.springframework.samples.petclinic.a.mapper;

import org.springframework.samples.petclinic.a.Product;
import org.springframework.samples.petclinic.a.dto.ProductServiceAccept;
import org.springframework.samples.petclinic.a.dto.ProductServiceResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose: Provides manual mapping functionality between {@link Product} entities and their DTOs ({@link ProductServiceAccept}, {@link ProductServiceResponse}).
 * How to use: Injected into services to convert data structures for different layers of the application.
 * This component handles the transformation of request DTOs to entities and entities to response DTOs.
 */
@Component
public class ProductMapper {

	/**
	 * Purpose: Default constructor.
	 * How to use: Spring will automatically create an instance of this component.
	 */
	public ProductMapper() {
	}

	/**
	 * Purpose: Converts a {@link ProductServiceAccept} DTO to a {@link Product} entity.
	 * How to use: Called by services when creating a new product or preparing an entity for an update from a request DTO.
	 * @param accept The DTO containing the product data to be mapped to an entity.
	 * @return A new Product entity populated with data from the accept DTO.
	 */
	public Product toEntity(ProductServiceAccept accept) {
		if (accept == null) {
			return null;
		}
		Product product = new Product();
		product.setName(accept.getName());
		product.setDescription(accept.getDescription());
		product.setPrice(accept.getPrice());
		product.setAvailable(accept.getAvailable());
		return product;
	}

	/**
	 * Purpose: Converts a {@link Product} entity to a {@link ProductServiceResponse} DTO.
	 * How to use: Called by services before returning product data to a controller, ensuring only necessary data is exposed.
	 * @param product The Product entity to be mapped to a response DTO.
	 * @return A new ProductServiceResponse DTO populated with data from the entity.
	 */
	public ProductServiceResponse toResponse(Product product) {
		if (product == null) {
			return null;
		}
		ProductServiceResponse response = new ProductServiceResponse();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setAvailable(product.getAvailable());
		return response;
	}

	/**
	 * Purpose: Converts a collection of {@link Product} entities to a list of {@link ProductServiceResponse} DTOs.
	 * How to use: Useful for converting query results from a repository into a format suitable for API responses.
	 * @param products A collection of Product entities.
	 * @return A list of ProductServiceResponse DTOs, each representing a product from the input collection.
	 */
	public List<ProductServiceResponse> toResponseList(Collection<Product> products) {
		if (products == null) {
			return List.of();
		}
		return products.stream().map(this::toResponse).collect(Collectors.toList());
	}

}