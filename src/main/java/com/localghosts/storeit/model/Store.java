package com.localghosts.storeit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @Column(name = "storeslug")
    private String storeslug;

    @Column(name = "storename")
    private String storename;

    @JsonIgnoreProperties("store")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller", referencedColumnName = "email")
    private Seller seller;

    @JsonIgnoreProperties("store")
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
}
