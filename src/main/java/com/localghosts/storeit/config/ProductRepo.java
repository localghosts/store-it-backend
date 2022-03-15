package com.localghosts.storeit.config;

import java.util.List;

import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
	Product findByProductID(Long productID);

	List<Product> findByCategory(Category category);
}
