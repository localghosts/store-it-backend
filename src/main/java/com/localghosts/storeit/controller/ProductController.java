package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class ProductController {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CategoryRepo categoryRepo;

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
}
