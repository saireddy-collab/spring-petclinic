package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

/**
 * Repository interface for {@link Product} instances.
 * Provides basic CRUD operations and custom query methods for products.
 */
public interface ProductRepository extends Repository<Product, Integer> {

	/**
	 * Saves a {@link Product} entity to the database.
	 * @param product The product to save.
	 * @return The saved product, which may have updated ID or other generated fields.
	 * @throws DataAccessException if there is an issue with data access.
	 */
	Product save(Product product) throws DataAccessException;

	/**
	 * Retrieves a {@link Product} by its ID.
	 * @param id The ID of the product to retrieve.
	 * @return An {@link Optional} containing the product if found, or empty if not found.
	 * @throws DataAccessException if there is an issue with data access.
	 */
	Optional<Product> findById(Integer id) throws DataAccessException;

	/**
	 * Retrieves all {@link Product} entities from the database.
	 * @return A {@link Collection} of all products.
	 * @throws DataAccessException if there is an issue with data access.
	 */
	Collection<Product> findAll() throws DataAccessException;

	/**
	 * Retrieves a {@link Product} by its name.
	 * @param name The name of the product to retrieve.
	 * @return An {@link Optional} containing the product if found, or empty if not found.
	 * @throws DataAccessException if there is an issue with data access.
	 */	
	Optional<Product> findByName(String name) throws DataAccessException;

	/**
	 * Deletes a {@link Product} by its ID.
	 * @param id The ID of the product to delete.
	 * @throws DataAccessException if there is an issue with data access.
	 */
	void deleteById(Integer id) throws DataAccessException;

}
