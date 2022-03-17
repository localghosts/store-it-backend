package com.localghosts.storeit.controller;

import java.util.List;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.OrderRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.Store;
import com.localghosts.storeit.model.UpdateStatusRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	BuyerRepo buyerRepo;

	@GetMapping("/store/{storeslug}/orders")
	public List<Order> getOrders(@PathVariable("storeslug") String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);
		return orderRepo.findByStore(store);
	}

	@PutMapping("/store/{storeslug}/orders/{orderid}/status")
	public void updateStatus(@PathVariable("orderid") Long orderid,
			@RequestBody UpdateStatusRequest updateStatusRequest) {
		Order order = orderRepo.findByOrderID(orderid);
		order.setStatus(updateStatusRequest.getStatus());
	}

	@GetMapping("/orders")
	public List<Order> getOrders(Authentication auth) {
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		return orderRepo.findByBuyer(buyer);
	}
}
