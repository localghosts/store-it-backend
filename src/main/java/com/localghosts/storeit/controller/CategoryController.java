package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.Store;
import com.localghosts.storeit.repo.CartRepo;
import com.localghosts.storeit.repo.CategoryRepo;
import com.localghosts.storeit.repo.SellerRepo;
import com.localghosts.storeit.repo.StoreRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Category should support Add, Delete and Toggle. These will be used by the seller
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	SellerRepo sellerRepo;

	@Autowired
	CartRepo cartRepo;

	@GetMapping("/store/{storeslug}/category")
	public List<Category> getCategories(@PathVariable String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);
		return categoryRepo.findByStore(store);
	}

	@PostMapping("/store/{storeslug}/category")
	public void addCategory(@RequestBody Category category, @PathVariable String storeslug) {
		if (Objects.isNull(category.getName()))
			throw new Error("Invalid category name");
		if (Objects.isNull(category.getImage()))
			throw new Error("Invalid category image");

		Store store = storeRepo.findByStoreslug(storeslug);

		List<Category> existing = categoryRepo.findByStoreAndName(store, category.getName());
		if (existing == null || existing.isEmpty()) {
			Category newCategory = new Category(category.getName(), category.getImage(), category.getDescription(),
					store);
			categoryRepo.save(newCategory);
		} else {
			throw new Error("Category already exist");
		}
	}

	@DeleteMapping("/store/{storeslug}/category/{categoryid}")
	public void deleteCategory(@PathVariable("categoryid") Long categoryid, Authentication auth) {
		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("You are not a seller");
		Category category = categoryRepo.findByCategoryID(categoryid);
		if (category == null)
			throw new Error("Category does not exist");
		if (!category.getStore().getSeller().equals(seller))
			throw new Error("You are not the owner of this store");
		List<Product> products = category.getProducts();
		deleteCartByProduct(products);
		categoryRepo.delete(category);
	}

	private void deleteCartByProduct(List<Product> products) {
		for (Product product : products) {
			List<Cart> carts = cartRepo.findByProduct(product);
			for (Cart cart : carts) {
				cartRepo.delete(cart);
			}
		}
	}

	@PutMapping("/store/{storeslug}/category/{categoryid}")
	public void toggleCategory(@PathVariable("categoryid") Long categoryid, @RequestBody Category updatedCategory) {
		Category category = categoryRepo.findByCategoryID(categoryid);
		if (category == null)
			throw new Error("Category not found");
		if (Objects.isNull(updatedCategory.isEnabled()))
			throw new Error("Invalid category name");

		category.setEnabled(updatedCategory.isEnabled());
		categoryRepo.save(category);
	}

}
