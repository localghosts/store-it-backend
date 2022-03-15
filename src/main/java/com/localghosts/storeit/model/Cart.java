package com.localghosts.storeit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cart") // @Table(name = "ta_bodystat", uniqueConstraints =
                      // {@UniqueConstraint(columnNames = {"id"})})
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerID", nullable = false)
    private Buyer buyer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreID", nullable = false)
    private Store store;

    public Cart(Buyer buyer, Store store, Product product, int quantity) {
        this.buyer = buyer;
        this.store = store;
        this.product = product;
        this.quantity = quantity;
    }

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", nullable = false)
    private Product product;

    private int quantity;

    public Cart() {
    }

    public Cart(Buyer buyer, Product product, int quantity) {
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
    }

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

    public int getAmount() {
        return product.getPrice() * quantity;
    }
}
