package com.localghosts.storeit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OTPs")
public class OTP {
	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "otp")
	private String otp;

	@Column(name = "used")
	private boolean used;

	public boolean getUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public OTP() {
	}

	public OTP(String otp, String email, boolean used) {
		super();
		this.email = email;
		this.otp = otp;
		this.used = used;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
