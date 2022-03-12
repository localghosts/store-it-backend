package com.localghosts.storeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localghosts.storeit.config.BuyerRepo;
import com.localghosts.storeit.config.OTPRepo;
import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.OTP;

@RestController
public class BuyerSignupController {
	
	@Autowired
	OTPRepo otprepo;
	
	@Autowired
	BuyerRepo buyerrepo;
	
	@RequestMapping("/BuyerSignup")
	public String BuyerSignup(Buyer buyer, String otp) {
		String email = buyer.getEmail();
		OTP otpentry = otprepo.findByEmail(email);
		if(otpentry == null)
			return "OTP generation failed";
		if(otpentry.getDone() != 0)
			return "Email Already registered";
		if(otpentry.getOtp().equals(otp) == false)
			return "Wrong otp";
		otpentry.setDone(1);
		otprepo.save(otpentry);
		buyerrepo.save(buyer);
		return "Sign up Successful";
	}
}
