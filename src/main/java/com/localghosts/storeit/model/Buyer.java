package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Buyers")
public class Buyer implements Serializable {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	public Buyer() {
	}

	public Buyer(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;

	@JsonIgnoreProperties("buyer")
	@OneToMany(mappedBy = "buyer")
	private List<Cart> carts;

	@JsonIgnoreProperties("buyer")
	@OneToMany(mappedBy = "buyer")
	private List<Order> orders;

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

	/**
	 * @return List<Cart> return the carts
	 */
	public List<Cart> getCarts() {
		return carts;
	}

	/**
	 * @param carts the carts to set
	 */
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	/**
	 * @return List<Order> return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
