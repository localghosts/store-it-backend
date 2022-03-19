package com.localghosts.storeit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.localghosts.storeit.model.OTP;

public interface OTPRepo extends JpaRepository<OTP, String> {
	OTP findByEmail(String email);
}