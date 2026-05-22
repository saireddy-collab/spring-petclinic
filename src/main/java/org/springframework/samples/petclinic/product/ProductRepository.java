package org.springframework.samples.petclinic.product;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository interface for managing {@link Product} entities.
 * Provides methods for retrieving, saving, and querying product data.
 * Extends Spring Data's {@link Repository} interface.
 */
public interface ProductRepository extends Repository<Product, Integer> {

	/**
	 * Retrieves all {@link Product} entities from the persistence layer.
	 * @return A collection of all products.
	 * @throws DataAccessException if there is an issue accessing the data.
	 */	
	Collection<Product> findAll() throws DataAccessException;

	/**
	 * Retrieves a {@link Product} entity by its ID.
	 * @param id The ID of the product to retrieve.
	 * @return An {@link Optional} containing the product if found, or empty if not found.
	 * @throws DataAccessException if there is an issue accessing the data.
	 */
	Optional<Product> findById(Integer id) throws DataAccessException;

	/**
	 * Saves a {@link Product} entity to the persistence layer.
	 * This method can be used for both creating new products and updating existing ones.
	 * @param product The product to save.
	 * @throws DataAccessException if there is an issue accessing the data.
	 */
	void save(Product product) throws DataAccessException;

}
