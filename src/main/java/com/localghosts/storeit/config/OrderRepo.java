package com.localghosts.storeit.config;

import java.util.List;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
	Order findByOrderID(Long id);

	List<Order> findByStore(Store store);

	List<Order> findByBuyer(Buyer buyer);
}
