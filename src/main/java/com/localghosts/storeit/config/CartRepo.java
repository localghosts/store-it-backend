package com.localghosts.storeit.config;

import com.localghosts.storeit.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
	Cart findByCartID(Long id);
}
