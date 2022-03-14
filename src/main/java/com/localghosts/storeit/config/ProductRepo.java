package com.localghosts.storeit.config;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.*;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	Product findByProductID(long Id);

}