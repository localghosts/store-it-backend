package com.localghosts.storeit.controller;

import java.util.List;

import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class StoreController {

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	SellerRepo sellerRepo;

	@GetMapping("/store")
	public List<Store> getStores() {
		return storeRepo.findAll();
	}

	@PostMapping("/store")
	public Store addStore(@RequestBody Store store, Authentication auth) {

		String storeslugString = makeStorestlug(store.getStorename());
		while (storeRepo.findByStoreslug(storeslugString) != null)
			storeslugString += storeslugString.charAt((int) (Math.random() * storeslugString.length()));

		Seller seller = sellerRepo.findByEmail(auth.getName());
		if (seller == null)
			throw new Error("Invalid Seller");

		Store storeobj = new Store(storeslugString, store.getStorename(), seller);
		System.out.println(storeobj);
		storeRepo.save(storeobj);

		return storeobj;
	}

	/*
	 * @PutMapping("/store/{storeslug}")
	 * public Store updateorsaveStore(@RequestBody Store newstore, @PathVariable
	 * String storeslug) {
	 * 
	 * Store store = storeRepo.findByStoreslug(storeslug);
	 * 
	 * if (store != null) {
	 * store.setStorename(store.getStorename());
	 * 
	 * if (storeRepo.save(store).getStoreslug().equals(storeslug)) {
	 * System.out.println("Succesfully saved");
	 * return store;
	 * }
	 * }
	 * 
	 * Store storeobj = new Store();
	 * return storeobj;
	 * }
	 */

	private String makeStorestlug(String storename) {
		String withoutspaceString = storename.replaceAll("\\s", "");
		String lowercaseaplhaString = withoutspaceString.replaceAll("[^a-zA-Z]", "").toLowerCase();
		return lowercaseaplhaString;
	}
}
