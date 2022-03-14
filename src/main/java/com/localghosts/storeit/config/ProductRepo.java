package com.localghosts.storeit.config;

import com.localghosts.storeit.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	Product findByProductID(long Id);

}