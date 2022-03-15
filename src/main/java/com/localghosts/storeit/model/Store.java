package com.localghosts.storeit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stores")
public class Store implements Serializable {

    @Id
    @Column(name = "storeslug")
    private String storeslug;

    @Column(name = "storename")
    private String storename;

    @Column(name = "storelogo")
    private String storelogo;

    @Column(name = "storebanner")
    private String storebanner;

    public Store(String storeslug, String storename, String storelogo, String storebanner, Seller seller) {
        this.storeslug = storeslug;
        this.storename = storename;
        this.storelogo = storelogo;
        this.storebanner = storebanner;
        this.seller = seller;
    }

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller", referencedColumnName = "email")
    private Seller seller;

    @JsonIgnore
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Category> categories;

    public Store(String storeslug, String storename, Seller seller) {
        this.storeslug = storeslug;
        this.storename = storename;
        this.seller = seller;
    }

    public Store() {
    }

    /**
     * @return String return the storeslug
     */
    public String getStoreslug() {
        return storeslug;
    }

    /**
     * @param storeslug the storeslug to set
     */
    public void setStoreslug(String storeslug) {
        this.storeslug = storeslug;
    }

    /**
     * @return String return the storename
     */
    public String getStorename() {
        return storename;
    }

    /**
     * @param storename the storename to set
     */
    public void setStorename(String storename) {
        this.storename = storename;
    }

    /**
     * @return Seller return the seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    /**
     * @return List<Category> return the categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * @return String return the storelogo
     */
    public String getStorelogo() {
        return storelogo;
    }

    /**
     * @param storelogo the storelogo to set
     */
    public void setStorelogo(String storelogo) {
        this.storelogo = storelogo;
    }

    /**
     * @return String return the storebanner
     */
    public String getStorebanner() {
        return storebanner;
    }

    /**
     * @param storebanner the storebanner to set
     */
    public void setStorebanner(String storebanner) {
        this.storebanner = storebanner;
    }

}
