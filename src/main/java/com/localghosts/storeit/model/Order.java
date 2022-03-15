package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerID", nullable = false)
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreID", nullable = false)
    private Store store;

    private String productName;
    private int productPrice;
    private int quantity;
    private Date orderDate;

    public enum Status {
        PLACED,
        ACCEPTED,
        PROCESSING,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    private Status status;

    public Order(Buyer buyer, Store store, String productName, int productPrice, int quantity) {
        this.buyer = buyer;
        this.store = store;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.orderDate = new Date();
        this.status = Status.PLACED;
    }

    public Order() {
    }

    public Order(Cart cart) {
        this.buyer = cart.getBuyer();
        this.store = cart.getStore();
        this.productName = cart.getProduct().getName();
        this.productPrice = cart.getProduct().getPrice();
        this.quantity = cart.getQuantity();
        this.orderDate = new Date();
        this.status = Status.PLACED;
    }

    /**
     * @return Long return the orderID
     */
    public Long getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(Long orderID) {
        this.orderID = orderID;
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
     * @return String return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return int return the productPrice
     */
    public int getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
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
     * @return Date return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderDate() {
        this.orderDate = new Date();
    }

    /**
     * @return Status return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus() {
        this.status = Status.ACCEPTED;
    }

}
