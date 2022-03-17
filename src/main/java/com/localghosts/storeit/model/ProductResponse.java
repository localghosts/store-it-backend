package com.localghosts.storeit.model;

public class ProductResponse {

	private Product product;

	public ProductResponse(Product product) {
		this.product = product;
	}

	public Long getProductID() {
		return product.getProductID();
	}

	public String getProductName() {
		return product.getName();
	}

	public int getPrice() {
		return product.getPrice();
	}

	public boolean getInStock() {
		return product.isInstock() && product.getCategory().isEnabled();
	}

	public String getCategoryName() {
		return product.getCategory().getName();
	}

	public String getStoreName() {
		return product.getCategory().getStore().getStorename();
	}

	public String getStoreSlug() {
		return product.getCategory().getStore().getStoreslug();
	}

}
