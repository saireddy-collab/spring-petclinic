package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC Controller for managing products in the PetClinic application.
 * Provides endpoints for listing, adding, updating, and viewing product details.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	private static final String VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";

	private final ProductService productService;

	/**
	 * Constructs a new ProductController with the given ProductService.
	 * @param productService The service for product business logic.
	 */
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * Initializes the form for creating a new product.
	 * @param model The model to add attributes to.
	 * @return The view name for the product creation form.
	 */
	@GetMapping("/new")
	public String initCreationForm(Map<String, Object> model) {
		Product product = new Product();
		model.put("product\