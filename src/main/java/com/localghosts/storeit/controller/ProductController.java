package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import com.localghosts.storeit.config.CartRepo;
import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/store/{storeslug}/{categoryid}")
	public List<Product> getProducts(@PathVariable("categoryid") Long categoryid) {
		Category category = categoryRepo.findByCategoryID(categoryid);
		return productRepo.findByCategory(category);
	}

	@PostMapping("/store/{storeslug}/{categoryid}")
	public void addProduct(@RequestBody Product product, @PathVariable("categoryid") Long categoryid) {
		if (Objects.isNull(product.getName()))
			throw new Error("Invalid product name");
		if (Objects.isNull(product.getPrice()) || product.getPrice() <= 0)
			throw new Error("Invalid product price");

		Category category = categoryRepo.findByCategoryID(categoryid);
		Product newProduct = new Product(product.getName(), product.getPrice(), category);

		productRepo.save(newProduct);
	}

	@DeleteMapping("/store/{storeslug}/product/{productid}")
	public void deleteProduct(@PathVariable("productid") Long productid) {
		Product product = productRepo.findByProductID(productid);
		List<Cart> carts = cartRepo.findByProduct(product);
		for (Cart cart : carts) {
			cartRepo.delete(cart);
		}
		productRepo.delete(product);
	}

	@PutMapping("/store/{storeslug}/product/{productid}/toggle")
	public void toggleProduct(@PathVariable("productid") Long productid) {
		Product product = productRepo.findByProductID(productid);
		product.setInstock(!product.isInstock());
		productRepo.save(product);
	}

}
