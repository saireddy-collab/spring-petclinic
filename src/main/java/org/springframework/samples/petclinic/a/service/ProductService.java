package org.springframework.samples.petclinic.a.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.samples.petclinic.a.Product;
import org.springframework.samples.petclinic.a.repository.ProductRepository;
import org.springframework.samples.petclinic.a.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Service class for managing Product entities.
 *
 * Purpose: Provides business logic for product-related operations,
 * interacting with the {@link ProductRepository} to perform CRUD actions
 * and encapsulate domain-specific rules.
 * How to use: Inject this service into controllers or other services
 * to perform operations on products.
 */
@Service
@Transactional(readOnly = true)
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    /**
     * Constructs a new ProductService with the given ProductRepository.
     *
     * Purpose: To inject the necessary repository dependency for data access.
     * How to use: Spring automatically injects ProductRepository when creating a ProductService bean.
     * @param productRepository The repository for product data.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the system.
     *
     * Purpose: To fetch a collection of all available products.
     * How to use: Call this method to get a list of all products for display or processing.
     * @return A collection of {@link Product} objects.
     */
    public Collection<Product> findAllProducts() {
        logger.info("Fetching all products.");
        return productRepository.findAll();
    }

    /**
     * Retrieves a single product by its unique identifier.
     *
     * Purpose: To find a specific product using its ID.
     * How to use: Call this method when you need to retrieve a product by its primary key.
     * @param id The unique identifier of the product.
     * @return The {@link Product} object if found.
     * @throws ProductNotFoundException if no product with the given ID exists.
     */
    public Product findProductById(Integer id) {
        logger.info("Fetching product with ID: {}", id);
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseThrow(() -> {
            logger.warn("Product with ID {} not found.", id);
            return new ProductNotFoundException(id);
        });
    }

    /**
     * Saves a product, either creating a new one or updating an existing one.
     *
     * Purpose: To persist a product entity. If the product has an ID, it updates an existing product;
     * otherwise, it creates a new product.
     * How to use: Pass a {@link Product} object to this method to save it to the database.
     * @param product The product to be saved.
     * @return The saved {@link Product} object, potentially with an updated ID if it was new.
     */
    @Transactional
    public Product saveProduct(Product product) {
        if (product.getId() == null) {
            logger.info("Creating new product: {}", product.getName());
        } else {
            logger.info("Updating product with ID: {}", product.getId());
        }
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * Purpose: To remove a product from the system.
     * How to use: Call this method with the ID of the product you wish to delete.
     * @param id The unique identifier of the product to be deleted.
     * @throws ProductNotFoundException if no product with the given ID exists.
     */
    @Transactional
    public void deleteProduct(Integer id) {
        logger.info("Attempting to delete product with ID: {}", id);
        if (!productRepository.existsById(id)) {
            logger.warn("Cannot delete product with ID {} as it does not exist.", id);
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        logger.info("Product with ID {} deleted successfully.", id);
    }

    /**
     * Finds a collection of products by their name.
     *
     * Purpose: To retrieve all products that match a given name.
     * How to use: Call this method with a product name to find matching products.
     * @param name The name of the product to search for.
     * @return A collection of {@link Product} objects with the specified name.
     */
    public Collection<Product> findProductsByName(String name) {
        logger.info("Fetching products by name: {}", name);
        return productRepository.findByName(name);
    }
}
