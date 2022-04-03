package com.localghosts.storeit.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Sellers")
public class Seller implements Serializable {

	@Column(name = "name")
	private String name;

	@Id
	@Column(name = "email")
	private String email;

	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;

	@JsonIgnoreProperties("seller")
	@OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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

	public Seller() {
	}

	public Seller(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
