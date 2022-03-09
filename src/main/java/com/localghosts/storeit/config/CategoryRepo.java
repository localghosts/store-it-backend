package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	Category findByCategoryID(long Id);
}
