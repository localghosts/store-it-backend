package com.localghosts.storeit.config;

import java.util.List;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.Cart;
import com.localghosts.storeit.model.Product;
import com.localghosts.storeit.model.Store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
	Cart findByCartID(Long id);

	List<Cart> findByBuyerAndStore(Buyer buyer, Store store);

	Cart findTop1ByBuyerAndStoreAndProduct(Buyer buyer, Store store, Product product);

	List<Cart> findByProduct(Product product);
}
