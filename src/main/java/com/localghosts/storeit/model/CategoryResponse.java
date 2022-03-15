package com.localghosts.storeit.model;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponse {

	private Category category;

	public CategoryResponse(Category category) {
		if (category.isEnabled()) {
			this.category = category;
		} else {
			category = new Category();
		}
	}

	public CategoryResponse() {
	}

	public String getName() {
		return category.getName();
	}

	public String getImage() {
		return category.getImage();
	}

	public Long getCategoryID() {
		return category.getCategoryID();
	}

	public List<Product> getProducts() {
		return category.getProducts().stream().filter(product -> product.isInstock() == true)
				.collect(Collectors.toList());
	}

	public void setCategory(Category category) {
		if (category.isEnabled())
			this.category = category;
		else
			this.category = new Category();
	}

}
