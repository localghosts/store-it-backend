package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.*;

public interface StoreRepo extends JpaRepository<Store, String> {
	Store findByStoreslug(String storeslug);
}