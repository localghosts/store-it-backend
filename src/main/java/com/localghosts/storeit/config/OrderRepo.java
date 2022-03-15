package com.localghosts.storeit.config;

import com.localghosts.storeit.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
	Order findByOrderID(Long id);
}
