package com.localghosts.storeit.repo;

import com.localghosts.storeit.model.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, String> {
	Seller findByEmail(String email);
}
