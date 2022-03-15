package com.localghosts.storeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import com.localghosts.storeit.config.JwtTokenUtil;
import com.localghosts.storeit.config.OTPRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.model.JwtResponse;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.Signup;

@RestController
@CrossOrigin()
public class SellerAuthController {

	@Autowired
	OTPRepo otpRepo;

	@Autowired
	SellerRepo sellerRepo;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@RequestMapping("/seller/signup")
	public ResponseEntity<JwtResponse> SellerSignup(@RequestBody Signup sellerSignup) throws Exception {
		validateSellerSignupBody(sellerSignup);

		String email = sellerSignup.getEmail();
		OTP otpentry = otpRepo.findByEmail(email);

		if (otpentry == null || otpentry.getUsed() == true)
			throw new Exception("OTP not found or already used");
		if (otpentry.getOtp().equals(sellerSignup.getOtp()) == false)
			throw new Exception("OTP not valid");

		otpentry.setUsed(true);
		otpRepo.save(otpentry);

		Seller seller = new Seller(sellerSignup.getName(), sellerSignup.getEmail(), sellerSignup.getPassword());
		sellerRepo.save(seller);

		final String token = jwtTokenUtil.generateToken(seller.getEmail());

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping("/seller/login")
	public ResponseEntity<JwtResponse> SellerLogin(@RequestBody Seller seller) throws Exception {
		validateSellerLoginBody(seller);

		Seller sellerFromRepo = sellerRepo.findByEmail(seller.getEmail());

		if (sellerFromRepo == null)
			throw new Exception("Seller not found");

		if (sellerFromRepo.getPassword().equals(seller.getPassword()) == false)
			throw new Exception("Password not valid");

		final String token = jwtTokenUtil.generateToken(seller.getEmail());

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void validateSellerLoginBody(Seller seller) throws Exception {
		if (Objects.isNull(seller.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(seller.getPassword()))
			throw new Exception("Password not found");
	}

	private void validateSellerSignupBody(Signup sellerSignup) throws Exception {
		if (Objects.isNull(sellerSignup.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(sellerSignup.getPassword()))
			throw new Exception("Password not found");
		if (Objects.isNull(sellerSignup.getOtp()))
			throw new Exception("OTP not found");
		if (Objects.isNull(sellerSignup.getName()))
			throw new Exception("Name not found");
	}
}
