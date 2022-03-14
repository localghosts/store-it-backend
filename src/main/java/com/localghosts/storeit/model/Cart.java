package com.localghosts.storeit.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyerID", nullable = false)
	private Buyer buyer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemID", nullable = false)
	private Product product;

	public Cart() {
	}

	public Cart(Buyer buyer, Product product, int quantity) {
		this.buyer = buyer;
		this.product = product;
		this.quantity = quantity;
	}

	private int quantity;

    /**
     * @return Long return the cartID
     */
    public Long getCartID() {
        return cartID;
    }

    /**
     * @param cartID the cartID to set
     */
    public void setCartID(Long cartID) {
        this.cartID = cartID;
    }

    /**
     * @return Buyer return the buyer
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * @param buyer the buyer to set
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * @return Product return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
