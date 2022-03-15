package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.List;

public class CartResponse implements Serializable {
	private Long total;
	private List<Cart> cartList;

	public CartResponse(Long total, List<Cart> cartList) {
		this.total = total;
		this.cartList = cartList;
	}

	public CartResponse() {
	}

	/**
	 * @return Long return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return List<Cart> return the cartList
	 */
	public List<Cart> getCartList() {
		return cartList;
	}

	/**
	 * @param cartList the cartList to set
	 */
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

}
