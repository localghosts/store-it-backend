package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryID;

	@Column(name = "name")
	private String name;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "image")
	private String image;

	@Column(name = "description")
	private String description;

	public Category(String name, String image, String description, Store store) {
		this.name = name;
		this.image = image;
		this.description = description;
		this.store = store;
		this.enabled = true;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store", nullable = false)
	private Store store;

	public Category(String name, String image, Store store) {
		this.name = name;
		this.image = image;
		this.store = store;
		this.enabled = true;
	}

	@JsonIgnoreProperties("category")
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;

	public Category() {
	}

	public Category(String name, Store store) {
		super();
		this.name = name;
		this.store = store;
		this.enabled = true;

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
		return "Category [categoryID=" + categoryID + ", name=" + name + ", enabled=" + enabled + "]";
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

	/**
	 * @return String return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return String return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
