package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderID;

    @JsonIgnoreProperties({ "orders", "carts", "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyerID", nullable = false)
    private Buyer buyer;

    @JsonIgnoreProperties({ "storebanner", "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreID", nullable = false)
    private Store store;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneno")
    private String phoneNo;

    @OneToMany
    private List<OrderItem> orderItems;

    @Column(name = "timestamp")
    private Date orderDate;

    public enum Status {
        PLACED,
        ACCEPTED,
        REJECTED,
        PROCESSING,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    @Column(name = "status")
    private Status status;

    public Order(Buyer buyer, Store store, String address, String phoneNo, List<OrderItem> orderItems) {
        this.buyer = buyer;
        this.store = store;
        this.address = address;
        this.phoneNo = phoneNo;
        this.orderItems = orderItems;
        this.orderDate = new Date();
        this.status = Status.PLACED;
    }

    public Order(Buyer buyer, Store store, String address) {
        this.buyer = buyer;
        this.store = store;
        this.orderDate = new Date();
        this.status = Status.PLACED;
    }

    public Order() {
    }

    public Order(Cart cart) {
        this.buyer = cart.getBuyer();
        this.store = cart.getStore();
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

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return List<OrderItem> return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * @return String return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getAmount() {
        Long amount = 0L;
        for (OrderItem item : orderItems) {
            amount += item.getProductPrice() * item.getQuantity();
        }
        return amount;
    }
}
