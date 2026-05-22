package org.springframework.samples.petclinic.a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.a.Product;
import java.util.Collection;

/**
 * Repository interface for managing Product entities.
 *
 * Purpose: Provides standard CRUD operations and custom query methods for the {@link Product} entity.
 * How to use: Inject this interface into service classes to interact with product data in the database.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Finds a collection of products by their name.
     *
     * Purpose: To retrieve all products that match a given name.
     * How to use: Call this method with a product name to find matching products.
     * @param name The name of the product to search for.
     * @return A collection of {@link Product} objects with the specified name.
     */
    Collection<Product> findByName(String name);
}
