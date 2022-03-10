package com.localghosts.storeit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long productID;
		
        @Column(name="name")
		private String name;
        
        @Column(name="price")
		private int price;
        
        
        @Column(name="categoryID")
		private int categoryID;
        
        @Column(name="instock")
		private boolean instock;
		
		
		public Product() {/**
			this.name = "na";
			this.price = 0;
			this.category = "naa";
			this.store = "na";
			this.inStock = false;*/
		}
		
		public Product(String name, int price, int categoryID,  boolean instock) {
			this.name = name;
			this.price = price;
			this.categoryID = categoryID;
			this.instock = instock;
		}
		
		public Long getProductID() {
			return productID;
		}
		public void setProductID(Long productID) {
			this.productID = productID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getCategoryID() {
			return categoryID;
		}
		public void setCategoryID(int categoryID) {
			this.categoryID = categoryID;
		}
		public boolean isInstock() {
			return instock;
		}
		public void setInstock(boolean instock) {
			this.instock = instock;
		}
		
		
		@Override
		public String toString() {
			return "Product [productId=" + productID + ", name=" + name + ", price=" + price + ", categoryID=" + categoryID
					 + ", inStock=" + instock + "]";
		}

		
		
		
		
}
