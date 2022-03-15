package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class CategoryController {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	StoreRepo storeRepo;

	@GetMapping("/store/{storeslug}/category")
	public List<Category> getCategories(@PathVariable String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);
		return categoryRepo.findByStore(store);
	}

	@PostMapping("/store/{storeslug}/category")
	public void addCategory(@RequestBody Category category, @PathVariable String storeslug) {
		if (Objects.isNull(category.getName()))
			throw new Error("Invalid category name");

		Store store = storeRepo.findByStoreslug(storeslug);

		List<Category> existing = categoryRepo.findByStoreAndName(store, category.getName());
		if (existing == null || existing.isEmpty()) {
			Category newCategory = new Category(category.getName(), store);
			categoryRepo.save(newCategory);
		} else {
			throw new Error("Category already exist");
		}
	}
}
