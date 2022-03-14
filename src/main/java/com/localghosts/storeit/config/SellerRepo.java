package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Seller;

public interface SellerRepo extends JpaRepository<Seller, String> {
	Seller findByEmail(String email);
}