package com.localghosts.storeit.config;

import com.localghosts.storeit.model.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, String> {
	Store findByStoreslug(String storeslug);
}