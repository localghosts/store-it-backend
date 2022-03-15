package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Store;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	Category findByCategoryID(long Id);

	List<Category> findByStore(Store store);

	List<Category> findByStoreAndName(Store store, String name);
}
