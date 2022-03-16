package com.localghosts.storeit.model;

public class SellerSignupResponse extends JwtResponse {
	private String storeSlug;

	public SellerSignupResponse(String storeSlug, String token) {
		super(token);
		this.storeSlug = storeSlug;
	}

	public String getStoreSlug() {
		return storeSlug;
	}

	public void setStoreSlug(String storeSlug) {
		this.storeSlug = storeSlug;
	}

}
