package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localghosts.storeit.model.Buyer;

public interface BuyerRepo extends JpaRepository<Buyer , String> 
{
	Buyer findByEmail(String email);
} 