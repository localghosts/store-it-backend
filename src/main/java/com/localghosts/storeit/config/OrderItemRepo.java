package com.localghosts.storeit.config;

import com.localghosts.storeit.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
	OrderItem findByItemId(Long id);
}
