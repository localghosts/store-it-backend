package com.localghosts.storeit.controller;

import java.util.List;

import com.localghosts.storeit.model.Store;
import com.localghosts.storeit.model.StoreResponse;
import com.localghosts.storeit.repo.SellerRepo;
import com.localghosts.storeit.repo.StoreRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class StoreController {

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	SellerRepo sellerRepo;

	@GetMapping("/stores")
	public List<Store> getStores() {
		return storeRepo.findAll();
	}

	@GetMapping("/store/{storeslug}")
	public StoreResponse getStore(@PathVariable String storeslug) {
		Store store = storeRepo.findByStoreslug(storeslug);

		return new StoreResponse(store);
	}
}
