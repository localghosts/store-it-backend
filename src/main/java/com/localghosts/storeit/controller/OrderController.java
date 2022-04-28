package com.localghosts.storeit.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.OrderItem;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.Store;
import com.localghosts.storeit.repo.BuyerRepo;
import com.localghosts.storeit.repo.OrderRepo;
import com.localghosts.storeit.repo.SellerRepo;
import com.localghosts.storeit.repo.StoreRepo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
	public List<Order> getOrders(@PathVariable("storeslug") String storeslug, Authentication auth) {
		return fetchOrders(storeslug, auth.getName());
	}

	private List<Order> fetchOrders(String storeslug, String email) throws Error {
		Seller seller = sellerRepo.findByEmail(email);
		Store store = storeRepo.findByStoreslug(storeslug);
		if (seller != null && store != null && store.getSeller().getEmail() == seller.getEmail()) {
			return orderRepo.findByStore(store);
		}
		throw new Error("Unauthorized");
	}

	//Helper function to format orders as CSV. Take care of Expections.
	@GetMapping("/store/{storeslug}/orderscsv")
	public void getOrdersCSV(@PathVariable("storeslug") String storeslug, Authentication auth,
			HttpServletResponse response) {
		List<Order> orders = fetchOrders(storeslug, auth.getName());
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"orders.csv\"");
		try (CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT)) {
			csvPrinter.printRecord("Order ID", "Order Date",
					"Product Name", "Product price", "Quantity",
					"Buyer email", "Buyer Phone",
					"Shipping Address", "Status");
			for (Order order : orders) {
				List<OrderItem> items = order.getOrderItems();
				for (OrderItem item : items) {
					csvPrinter.printRecord(order.getOrderID(), order.getOrderDate(),
							item.getProductName(), item.getProductPrice(), item.getQuantity(),
							order.getBuyer().getEmail(), order.getPhoneNo(),
							order.getAddress(), order.getStatus());
				}
			}
		} catch (Exception e) {
			throw new Error("CSV" + e.getMessage(), e);
		}

	}
	
	//Update status as requested by the seller.
	@PutMapping("/store/{storeslug}/order/{orderid}")
	public void updateStatus(@PathVariable("orderid") Long orderid,
			@RequestBody Order updatedOrder, Authentication auth) {
		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("Seller not found");

		Order order = orderRepo.findByOrderID(orderid);
		if (order == null)
			throw new Error("Order not found");
		//Only seller has the authorisation for this change
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
