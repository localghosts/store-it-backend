package com.localghosts.storeit.controller;

import java.util.List;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.CartRepo;
import com.localghosts.storeit.config.OrderRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.CartResponse;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class CartController {
	@Autowired
	StoreRepo storeRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CartRepo cartRepo;

	@Autowired
	BuyerRepo buyerRepo;

	@Autowired
	OrderRepo orderRepo;

	@PostMapping("/store/{storeslug}/cart/{productid}/{quantity}")
	public String addToCart(@PathVariable("storeslug") String storeslug, @PathVariable("productid") Long productid,
			@PathVariable("quantity") int quantity, Authentication auth) {

		if (quantity < 0)
			throw new Error("Invalid quantity");

		Product product = productRepo.findByProductID(productid);
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		Store store = storeRepo.findByStoreslug(storeslug);

		if (product == null || buyer == null || store == null)
			throw new Error("Invalid request");

		Cart cart = cartRepo.findTop1ByBuyerAndStoreAndProduct(buyer, store, product);

		if (cart == null) {
			if (quantity == 0)
				throw new Error("Invalid quantity");
			cartRepo.save(new Cart(buyer, store, product, quantity));
		}

		if (quantity == 0)
			cartRepo.delete(cart);

		cart.setQuantity(quantity);
		cartRepo.save(cart);

		return "Product added to cart";
	}

	@GetMapping("/store/{storeslug}/cart")
	public CartResponse getCart(@PathVariable("storeslug") String storeslug, Authentication auth) {
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		Store store = storeRepo.findByStoreslug(storeslug);
		if (buyer == null || store == null)
			throw new Error("Invalid request");
		List<Cart> cartlist = cartRepo.findByBuyerAndStore(buyer, store);
		Long total = cartlist.stream().mapToLong(Cart::getAmount).sum();
		CartResponse cartResponse = new CartResponse(total, cartlist);
		return cartResponse;
	}

	@PostMapping("/store/{storeslug}/checkout")
	public String checkout(@PathVariable("storeslug") String storeslug, Authentication auth) {
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		Store store = storeRepo.findByStoreslug(storeslug);

		if (buyer == null || store == null)
			throw new Error("Invalid request");

		List<Cart> cartlist = cartRepo.findByBuyerAndStore(buyer, store);

		for (Cart cart : cartlist) {
			orderRepo.save(new Order(cart));
			cartRepo.delete(cart);
		}

		return "Order placed successfully";
	}
}
