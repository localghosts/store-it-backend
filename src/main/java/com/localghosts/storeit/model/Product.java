package com.localghosts.storeit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productID;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private int price;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category", nullable = false)
	private Category category;

	@Column(name = "instock")
	private boolean instock;

	public Product(String name, int price, Category category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.instock = true;
	}

	public Product() {
	}

	@Override
	public String toString() {
		return "Product [productId=" + productID + ", name=" + name + ", price=" + price + ", categoryID="
				+ category.getCategoryID() + ", inStock=" + instock + "]";
	}

	/**
	 * @return Long return the productID
	 */
	public Long getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(Long productID) {
		this.productID = productID;
	}

	/**
	 * @return String return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return int return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return Category return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return boolean return the instock
	 */
	public boolean isInstock() {
		return instock;
	}

	/**
	 * @param instock the instock to set
	 */
	public void setInstock(boolean instock) {
		this.instock = instock;
	}

}
