package com.localghosts.storeit.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "stores")
public class Store {

	@Id
	@Column(name = "storeslug")
	private String storeslug;

	@Column(name = "storename")
	private String storename;

	@OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
	private Seller seller;

	public Store() {
	}

	public Store(String storeslug, String storename, List<Product> products, List<Category> categories) {
		super();
		this.storeslug = storeslug;
		this.storename = storename;
		// TODO to be extracted from JWT
		this.products = products;
		this.categories = categories;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_store_fk", referencedColumnName = "storeslug")
	private List<Product> products = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_store_fk", referencedColumnName = "storeslug")
	private List<Category> categories = new ArrayList<>();

	public String getStoreslug() {
		return storeslug;
	}

	public void setStoreslug(String storeslug) {
		this.storeslug = storeslug;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}
