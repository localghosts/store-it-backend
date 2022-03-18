package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.localghosts.storeit.config.CartRepo;
import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.ProductResponse;
import com.localghosts.storeit.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	CartRepo cartRepo;

	@Autowired
	SellerRepo sellerRepo;

	@GetMapping("/store/{storeslug}/{categoryid}")
	public List<Product> getProducts(@PathVariable("categoryid") Long categoryid) {
		Category category = categoryRepo.findByCategoryID(categoryid);
		return productRepo.findByCategory(category);
	}

	@PostMapping("/store/{storeslug}/{categoryid}")
	public void addProduct(@RequestBody Product product, @PathVariable("categoryid") Long categoryid,
			Authentication auth) {

		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("You are not a seller");

		Category category = categoryRepo.findByCategoryID(categoryid);
		if (category == null)
			throw new Error("Invalid category");

		if (!category.getStore().equals(seller.getStore()))
			throw new Error("You are not a seller of this store");

		if (product.getName() == null || product.getName().isEmpty())
			throw new Error("Invalid product name");
		if (Objects.isNull(product.getPrice()) || product.getPrice() <= 0)
			throw new Error("Invalid product price");

		Product newProduct = new Product(product.getName(), product.getPrice(), category);

		productRepo.save(newProduct);
	}

	@DeleteMapping("/store/{storeslug}/product/{productid}")
	public void deleteProduct(@PathVariable("productid") Long productid, Authentication auth) {

		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (Objects.isNull(seller))
			throw new Error("Invalid seller");

		Product product = productRepo.findByProductID(productid);
		if (Objects.isNull(product))
			throw new Error("Invalid product");
		if (!product.getCategory().getStore().equals(seller.getStore()))
			throw new Error("You are not authorized to delete this product");

		List<Cart> carts = cartRepo.findByProduct(product);
		for (Cart cart : carts)
			cartRepo.delete(cart);

		productRepo.delete(product);
	}

	@PutMapping("/store/{storeslug}/product/{productid}/toggle")
	public void toggleProduct(@RequestBody Product updatedProduct, @PathVariable("productid") Long productid,
			Authentication auth) {
		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("You are not authorized to update this product");

		Product product = productRepo.findByProductID(productid);
		if (!product.getCategory().getStore().equals(seller.getStore())) {
			throw new Error("You are not authorized to update this product");
		}

		Objects.requireNonNull(updatedProduct.isInstock());
		product.setInstock(updatedProduct.isInstock());
		productRepo.save(product);
	}

	@GetMapping("/products")
	public List<ProductResponse> getProducts(@RequestParam("name") String name) {
		return productRepo.findTop3ByNameIgnoreCaseContaining(name).stream()
				.map(product -> new ProductResponse(product))
				.collect(Collectors.toList());
	}

}
