package com.localghosts.storeit.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String jwttoken;

	public JwtResponse() {
	}

	public JwtResponse(String jwttoken) {
		this.jwttoken = "Bearer " + jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	/**
	 * @return String return the jwttoken
	 */
	public String getJwttoken() {
		return jwttoken;
	}

	/**
	 * @param jwttoken the jwttoken to set
	 */
	public void setJwttoken(String jwttoken) {
		this.jwttoken = "Bearer " + jwttoken;
	}
}
