package org.springframework.samples.petclinic.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for managing products in the PetClinic application.
 * Provides methods for creating, updating, displaying, and listing products.
 * Follows a traditional Spring MVC pattern, returning {@link ModelAndView} objects.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

	/**
	 * The {@link ProductRepository} used for data access operations related to products.
	 * How to use: Injected via constructor to interact with the database.
	 */
	private final ProductRepository productRepository;

	/**
	 * Constructs a new {@code ProductController} with the given {@link ProductRepository}.
	 * @param productRepository The repository for product data access.
	 */
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Initializes the {@link WebDataBinder} for form processing.
	 * Disables the ID field to prevent direct manipulation by users.
	 * @param dataBinder The data binder to initialize.
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Initializes a new form for product creation.
	 * Sets up a new {@link Product} object in the model for the form to bind to.
	 * How to use: Accessible via a GET request to '/products/new'.
	 * @param model A {@link Map} to add attributes for the view.
	 * @return The logical view name 'products/createOrUpdateProductForm'.
	 */
	@GetMapping("/new")
	public String initCreationForm(Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		return "products/createOrUpdateProductForm";
	}

	/**
	 * Processes the product creation form.
	 * Validates the submitted product data and saves it if valid. If there are errors,
	 * the form is redisplayed. On success, redirects to the product details page.
	 * How to use: Accessible via a POST request to '/products/new'.
	 * @param product The {@link Product} object populated from the form submission.
	 * @param result The {@link BindingResult} for validation errors.
	 * @param model A {@link Map} to add attributes for the view.
	 * @return A redirect URL or the form view name if validation fails.
	 */
	@PostMapping("/new")
	public String processCreationForm(@Valid Product product, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			return "products/createOrUpdateProductForm";
		}
		else {
			this.productRepository.save(product);
			return "redirect:/products/" + product.getId();
		}
	}

	/**
	 * Initializes the form for updating an existing product.
	 * Retrieves the product by ID and adds it to the model for the form.
	 * How to use: Accessible via a GET request to '/products/{productId}/edit'.
	 * @param productId The ID of the product to update.
	 * @param model The {@link Model} to add attributes for the view.
	 * @return The logical view name 'products/createOrUpdateProductForm'.
	 */
	@GetMapping("/{productId}/edit")
	public String initUpdateForm(@PathVariable("productId") int productId, Model model) {
		Optional<Product> product = this.productRepository.findById(productId);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
		} else {
			// Handle case where product is not found, e.g., redirect to error or list
			return "redirect:/products"; 
		}
		return "products/createOrUpdateProductForm";
	}

	/**
	 * Processes the form for updating an existing product.
	 * Validates the submitted product data and updates it if valid. If there are errors,
	 * the form is redisplayed. On success, redirects to the updated product's details page.
	 * How to use: Accessible via a POST request to '/products/{productId}/edit'.
	 * @param product The {@link Product} object populated from the form submission.
	 * @param result The {@link BindingResult} for validation errors.
	 * @param productId The ID of the product being updated.
	 * @return A redirect URL or the form view name if validation fails.
	 */
	@PostMapping("/{productId}/edit")
	public String processUpdateForm(@Valid Product product, BindingResult result, @PathVariable("productId") int productId) {
		if (result.hasErrors()) {
			return "products/createOrUpdateProductForm";
		}
		else {
			product.setId(productId); // Ensure the ID is set for update
			this.productRepository.save(product);
			return "redirect:/products/{productId}";
		}
	}

	/**
	 * Displays a single product's details.
	 * Retrieves the product by its ID and adds it to the {@link ModelAndView}.
	 * How to use: Accessible via a GET request to '/products/{productId}'.
	 * @param productId The ID of the product to display.
	 * @return A {@link ModelAndView} containing the product details.
	 */
	@GetMapping("/{productId}")
	public ModelAndView showProduct(@PathVariable("productId") int productId) {
		ModelAndView mav = new ModelAndView("products/productDetails");
		Optional<Product> product = this.productRepository.findById(productId);
		if (product.isPresent()) {
			mav.addObject(product.get());
		} else {
			// Handle case where product is not found, e.g., add error message or redirect
			// For simplicity, will just return a view without the product if not found
			mav.setViewName("products/productList"); // Redirect to list if not found
		}
		return mav;
	}

	/**
	 * Lists all available products.
	 * Retrieves all products from the repository and adds them to the {@link ModelAndView}.
	 * How to use: Accessible via a GET request to '/products'.
	 * @return A {@link ModelAndView} containing a collection of all products.
	 */
	@GetMapping({"", "/", "/list"})
	public ModelAndView showProductList() {
		ModelAndView mav = new ModelAndView("products/productList");
		Collection<Product> products = this.productRepository.findAll();
		mav.addObject("products", products);
		return mav;
	}
}
