package com.localghosts.storeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localghosts.storeit.config.OTPRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.model.Seller;

@RestController
public class SellerSignupController {
	
	@Autowired
	OTPRepo otprepo;
	
	@Autowired
	SellerRepo sellerrepo;
	
	@RequestMapping("/SellerSignup")
	public String SellerSignup(Seller seller, String otp) {
		String email = seller.getEmail();
		OTP otpentry = otprepo.findByEmail(email);
		if(otpentry == null)
			return "OTP generation failed";
		if(otpentry.getDone() != 0)
			return "Email Already registered";
		if(otpentry.getOtp().equals(otp) == false)
			return "Wrong otp";
		otpentry.setDone(1);
		otprepo.save(otpentry);
		sellerrepo.save(seller);
		return "Sign up Successful";
	}
}
