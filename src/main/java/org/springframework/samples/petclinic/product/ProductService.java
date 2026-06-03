package org.springframework.samples.petclinic.product;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing {@link Product} entities.
 * Handles business logic related to products, interacting with {@link ProductRepository}.
 */
@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepository;

	/**
	 * Constructs a new ProductService with the given ProductRepository.
	 * @param productRepository The repository for product data access.
	 */	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Retrieves a product by its unique ID.
	 * @param productId The ID of the product to retrieve.
	 * @return The {@link Product} found.
	 * @throws ProductNotFoundException if no product with the given ID is found.
	 */	
	@Transactional(readOnly = true)
	public Product findProductById(Integer productId) {
		logger.info("Attempting to find product with ID {}\