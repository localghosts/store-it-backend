package com.localghosts.storeit.model;

import java.util.List;
import java.util.stream.Collectors;

public class StoreResponse {
	private Store store;

	public StoreResponse(Store store) {
		this.store = store;
	}

	public StoreResponse() {
	}

	public List<CategoryResponse> getCategories() {
		return store.getCategories().stream().filter(category -> category.isEnabled() == true)
				.map(category -> new CategoryResponse(category))
				.filter(category -> category.getProducts().size() > 0)
				.collect(Collectors.toList());
	}

	/**
	 * @return Store return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

}
