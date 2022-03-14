package com.localghosts.storeit.config;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localghosts.storeit.model.OTP;

public interface OTPRepo extends JpaRepository<OTP, String> {
	OTP findByEmail(String email);
}