package com.localghosts.storeit.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Store;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryID;

	@Column(name = "name")
	private String name;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_category_fk", referencedColumnName = "categoryID")
	private List<Product> products = new ArrayList<>();

	public Category() {
	}

	public Category(String name, boolean enabled) {
		super();
		this.name = name;
		this.enabled = enabled;

	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", name=" + name + ", enabled=" + enabled
				+ "]";
	}

}
