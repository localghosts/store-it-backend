package com.localghosts.storeit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Buyers")
public class Buyer {

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "buyer")
	private List<Cart> carts;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    /**
     * @return List<Cart> return the carts
     */
    public List<Cart> getCarts() {
        return carts;
    }

    /**
     * @param carts the carts to set
     */
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}
