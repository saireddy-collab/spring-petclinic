package org.springframework.samples.petclinic.a.service.impl;

import org.springframework.samples.petclinic.a.Product;
import org.springframework.samples.petclinic.a.exception.ProductNotFoundException;
import org.springframework.samples.petclinic.a.mapper.ProductMapper;
import org.springframework.samples.petclinic.a.repository.ProductRepository;
import org.springframework.samples.petclinic.a.service.ProductService;
import org.springframework.samples.petclinic.a.dto.ProductServiceAccept;
import org.springframework.samples.petclinic.a.dto.ProductServiceResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Purpose: Provides the concrete implementation for {@link ProductService}.
 * How to use: Handles business logic for product management, orchestrating data access via {@link ProductRepository}
 * and DTO mapping via {@link ProductMapper}.
 */
@Service
public class ProductServiceImpl implements ProductService {

	/**
	 * Purpose: Logger for logging business events and exceptions.
	 * How to use: Used to record information, warnings, and errors during product operations.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	/**
	 * Purpose: Provides data access operations for Product entities.
	 * How to use: Injected to perform database CRUD operations on products.
	 */
	private final ProductRepository productRepository;

	/**
	 * Purpose: Handles mapping between Product entities and their DTOs.
	 * How to use: Injected to convert between request/response DTOs and database entities.
	 */
	private final ProductMapper productMapper;

	/**
	 * Purpose: Constructs a new ProductServiceImpl with necessary dependencies.
	 * How to use: Spring automatically injects ProductRepository and ProductMapper.
	 * @param productRepository The repository for product data access.
	 * @param productMapper The mapper for product DTO-entity conversions.
	 */
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	/**
	 * Purpose: Saves a new product or updates an existing one.
	 * How to use: Creates a new Product entity from the accept DTO and persists it.
	 * @param accept The DTO containing product data.
	 * @return The response DTO of the saved/updated product.
	 */
	@Override
	@Transactional
	public ProductServiceResponse saveProduct(ProductServiceAccept accept) {
		logger.info("Creating new product with name {}", accept.getName());
		Product product = productMapper.toEntity(accept);
		Product savedProduct = productRepository.save(product);
		logger.info("Product with id {} created successfully", savedProduct.getId());
		return productMapper.toResponse(savedProduct);
	}

	/**
	 * Purpose: Updates an existing product.
	 * How to use: Fetches an existing product, applies updates from the DTO, and saves it.
	 * @param productId The ID of the product to update.
	 * @param accept The DTO with updated product data.
	 * @return The response DTO of the updated product.
	 * @throws ProductNotFoundException if the product does not exist.
	 */
	@Override
	@Transactional
	public ProductServiceResponse updateProduct(Long productId, ProductServiceAccept accept) {
		logger.info("Attempting to update product with id {}", productId);
		Product existingProduct = productRepository.findById(productId)
			.orElseThrow(() -> new ProductNotFoundException(productId));

		existingProduct.setName(accept.getName());
		existingProduct.setDescription(accept.getDescription());
		existingProduct.setPrice(accept.getPrice());
		existingProduct.setAvailable(accept.getAvailable());

		Product updatedProduct = productRepository.save(existingProduct);
		logger.info("Product with id {} updated successfully", updatedProduct.getId());
		return productMapper.toResponse(updatedProduct);
	}

	/**
	 * Purpose: Retrieves a product by its ID.
	 * How to use: Queries the repository for a product by its unique identifier.
	 * @param productId The ID of the product to find.
	 * @return The response DTO of the found product.
	 * @throws ProductNotFoundException if the product does not exist.
	 */
	@Override
	@Transactional(readOnly = true)
	public ProductServiceResponse findProductById(Long productId) {
		logger.info("Fetching product with id {}", productId);
		Optional<Product> product = productRepository.findById(productId);
		return product.map(productMapper::toResponse)
			.orElseThrow(() -> new ProductNotFoundException(productId));
	}

	/**
	 * Purpose: Retrieves all products.
	 * How to use: Fetches all product entities from the database.
	 * @return A list of response DTOs for all products.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ProductServiceResponse> findAllProducts() {
		logger.info("Fetching all products");
		List<Product> products = productRepository.findAll();
		return productMapper.toResponseList(products);
	}

	/**
	 * Purpose: Deletes a product by its ID.
	 * How to use: Removes a product from the database based on its unique identifier.
	 * @param productId The ID of the product to delete.
	 * @throws ProductNotFoundException if the product does not exist.
	 */
	@Override
	@Transactional
	public void deleteProduct(Long productId) {
		logger.info("Attempting to delete product with id {}", productId);
		if (!productRepository.existsById(productId)) {
			throw new ProductNotFoundException(productId);
		}
		productRepository.deleteById(productId);
		logger.info("Product with id {} deleted successfully", productId);
	}

}