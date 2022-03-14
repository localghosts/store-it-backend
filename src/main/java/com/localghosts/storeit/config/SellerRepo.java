package com.localghosts.storeit.config;

import com.localghosts.storeit.model.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, String> {
	Seller findByEmail(String email);
}