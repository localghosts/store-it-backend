package com.localghosts.storeit.controller;

import java.util.List;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.CartRepo;
import com.localghosts.storeit.config.CategoryRepo;
import com.localghosts.storeit.config.OrderRepo;
import com.localghosts.storeit.config.ProductRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.CartResponse;
import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Order;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	CartRepo cartRepo;

	@Autowired
	BuyerRepo buyerRepo;

	@Autowired
	OrderRepo orderRepo;

	@GetMapping("/store")
	public List<Store> getStores() {
		return storeRepo.findAll();
	}

	@PostMapping("/store")
	public Store addStore(@RequestBody Store store) {
		// make the storeslug
		String storeslugString = makeStorestlug(store.getStorename());
		Store storecheck = new Store();

		// while loop that computes till a unique storeslug is found
		while (storecheck != null) {
			storeslugString += "_";
			storecheck = storeRepo.findByStoreslug(storeslugString);

		}

		Store storeobj = new Store();
		storeobj.setStoreslug(storeslugString);
		storeobj.setSeller(store.getSeller());
		storeobj.setStorename(store.getStorename());
		storeRepo.save(storeobj);
		System.out.println(storeobj);
		return storeobj;
	}

	@PutMapping("/store/{storeslug}")
	public Store updateorsaveStore(@RequestBody Store newstore, @PathVariable String storeslug) {

		Store store = storeRepo.findByStoreslug(storeslug);

		if (store != null) {
			// these fields should be immutable
			// store.setStoreslug(store.getStoreslug());
			// store.setSellerid(store.getSellerid());
			store.setStorename(store.getStorename());

			if (storeRepo.save(store).getStoreslug().equals(storeslug)) {
				System.out.println("Succesfully saved");
				return store;
			}
		}

		System.out.println("no prev entry - hence just saving");
		Store storeobj = new Store();
		return storeobj;
	}

	@GetMapping("/store/{storeslug}/product")
	public List<Product> getStoresProducts(@PathVariable String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);

		List<Product> productlist = store.getProducts();
		return productlist;
	}

	@PostMapping("/store/{storeslug}/product")
	public Product addStoreProducts(@RequestBody Product product, @PathVariable String storeslug) {
		// adding product to the category
		Product newprod = new Product(product.getName(), product.getPrice(), product.getCategoryID(),
				product.isInstock());
		Category category = categoryRepo.findByCategoryID(product.getCategoryID());
		category.getProducts().add(newprod);
		categoryRepo.save(category);

		System.out.println(newprod);

		// adding product to the store field
		// Store store = repo.findByStoreslug(storeslug);
		// store.getProducts().add(newprod);
		// repo.save(store);
		return newprod;

	}

	@GetMapping("/store/{storeslug}/category")
	public List<Category> getStoresCategories(@PathVariable String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);
		List<Category> categorylist = store.getCategories();
		return categorylist;
	}

	@PostMapping("/store/{storeslug}/category")
	public Store addStoreCategories(@RequestBody Category category, @PathVariable String storeslug) {
		// TODO does it replace all the prev categories
		Category newcategory = new Category(category.getName(), category.isEnabled());
		Store store = storeRepo.findByStoreslug(storeslug);
		System.out.println(newcategory);
		store.getCategories().add(newcategory);
		storeRepo.save(store);
		return store;

	}

	public String makeStorestlug(String storename) {
		String withoutspaceString = storename.replaceAll("\\s", "");
		String lowercaseaplhaString = withoutspaceString.replaceAll("[^a-zA-Z]", "").toLowerCase();
		return lowercaseaplhaString;
	}

	@PostMapping("/store/{storeslug}/cart/{productid}/{quantity}")
	public String addToCart(@PathVariable("storeslug") String storeslug, @PathVariable("productid") long productid,
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

	@GetMapping("/store/{storeslug}/cart/")
	public CartResponse getCart(@PathVariable("storeslug") String storeslug, Authentication auth) {
		Buyer buyer = buyerRepo.findByEmail(auth.getName());
		Store store = storeRepo.findByStoreslug(storeslug);
		if (buyer == null || store == null)
			throw new Error("Invalid request");
		List<Cart> cartlist = cartRepo.findByBuyerAndStore(buyer, store);
		Long total = cartlist.stream().mapToLong(Cart::getPrice).sum();
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
