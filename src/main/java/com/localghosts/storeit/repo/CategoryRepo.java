package com.localghosts.storeit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.localghosts.storeit.model.Category;
import com.localghosts.storeit.model.Store;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	Category findByCategoryID(Long Id);

	List<Category> findByStore(Store store);

	List<Category> findByStoreAndName(Store store, String name);
}
