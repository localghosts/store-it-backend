package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.OrderRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.Store;

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

	@Autowired
	SellerRepo sellerRepo;

	@GetMapping("/store/{storeslug}/orders")
	public List<Order> getOrders(@PathVariable("storeslug") String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);
		return orderRepo.findByStore(store);
	}

	@PutMapping("/store/{storeslug}/order/{orderid}")
	public void updateStatus(@PathVariable("orderid") Long orderid,
			@RequestBody Order updatedOrder, Authentication auth) {
		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("Seller not found");

		Order order = orderRepo.findByOrderID(orderid);
		if (order == null)
			throw new Error("Order not found");
		if (!order.getStore().getSeller().equals(seller))
			throw new Error("You are not authorized to update this order");

		Objects.requireNonNull(updatedOrder.getStatus());
		order.setStatus(updatedOrder.getStatus());
		orderRepo.save(order);
	}

	@GetMapping("/orders")
	public List<Order> getOrders(Authentication auth) {
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		return orderRepo.findByBuyer(buyer);
	}
}
