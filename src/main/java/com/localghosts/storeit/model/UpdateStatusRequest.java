package com.localghosts.storeit.model;

public class UpdateStatusRequest {

	private Order.Status status;

	/**
	 * @return Order.Status return the status
	 */
	public Order.Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Order.Status status) {
		this.status = status;
	}

}
