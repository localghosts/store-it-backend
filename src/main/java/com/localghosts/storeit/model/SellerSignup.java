package com.localghosts.storeit.model;

public class SellerSignup {

	private String name;

	private String email;

	private String password;

	private String otp;

	private String storename;

	private String storebanner;

	private String storelogo;

	public SellerSignup(String name, String email, String password, String otp, String storename,
			String storebanner, String storelogo) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.storename = storename;
		this.storebanner = storebanner;
		this.storelogo = storelogo;
	}

	public SellerSignup(String name, String email, String password, String otp) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.otp = otp;
	}

	public SellerSignup() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtp() {
		return otp;
	}

	/**
	 * @return String return the storename
	 */
	public String getStorename() {
		return storename;
	}

	/**
	 * @param storename the storename to set
	 */
	public void setStorename(String storename) {
		this.storename = storename;
	}

	/**
	 * @return String return the storebanner
	 */
	public String getStorebanner() {
		return storebanner;
	}

	/**
	 * @param storebanner the storebanner to set
	 */
	public void setStorebanner(String storebanner) {
		this.storebanner = storebanner;
	}

	/**
	 * @return String return the storelogo
	 */
	public String getStorelogo() {
		return storelogo;
	}

	/**
	 * @param storelogo the storelogo to set
	 */
	public void setStorelogo(String storelogo) {
		this.storelogo = storelogo;
	}

}
