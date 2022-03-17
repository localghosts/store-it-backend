package com.localghosts.storeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import com.localghosts.storeit.config.JwtTokenUtil;
import com.localghosts.storeit.config.OTPRepo;
import com.localghosts.storeit.config.SellerRepo;
import com.localghosts.storeit.config.StoreRepo;
import com.localghosts.storeit.model.JwtResponse;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.model.Seller;
import com.localghosts.storeit.model.SellerSignup;
import com.localghosts.storeit.model.SellerSignupResponse;
import com.localghosts.storeit.model.Store;

@RestController
@CrossOrigin(origins = "*")
public class SellerAuthController {

	@Autowired
	OTPRepo otpRepo;

	@Autowired
	SellerRepo sellerRepo;

	@Autowired
	StoreRepo storeRepo;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/seller/signup")
	public ResponseEntity<JwtResponse> SellerSignup(@RequestBody SellerSignup sellerSignup) throws Exception {
		validateSellerSignupBody(sellerSignup);

		// String email = sellerSignup.getEmail();
		// OTP otpentry = otpRepo.findByEmail(email);

		// if (otpentry == null || otpentry.getUsed() == true)
		// 	throw new Exception("OTP not found or already used");
		// if (otpentry.getOtp().equals(sellerSignup.getOtp()) == false)
		// 	throw new Exception("OTP not valid");

		String encryptedPassword = bCryptPasswordEncoder.encode(sellerSignup.getPassword());

		Seller seller = new Seller(sellerSignup.getName(), sellerSignup.getEmail(), encryptedPassword);
		seller = sellerRepo.save(seller);

		// otpentry.setUsed(true);
		// otpRepo.save(otpentry);

		String storeslugString = makeStorestlug(sellerSignup.getStorename());
		while (storeRepo.findByStoreslug(storeslugString) != null)
			storeslugString += storeslugString.charAt((int) (Math.random() * storeslugString.length()));

		Store storeobj = new Store(storeslugString, sellerSignup.getStorename(), sellerSignup.getStorelogo(),
				sellerSignup.getStorebanner(), seller);
		storeRepo.save(storeobj);

		final String token = jwtTokenUtil.generateToken(seller.getEmail());

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping("/seller/login")
	public ResponseEntity<JwtResponse> SellerLogin(@RequestBody Seller seller) throws Exception {
		validateSellerLoginBody(seller);

		Seller sellerFromRepo = sellerRepo.findByEmail(seller.getEmail());

		if (sellerFromRepo == null)
			throw new Exception("Seller not found");

		if (bCryptPasswordEncoder.matches(seller.getPassword(), sellerFromRepo.getPassword()) == false)
			throw new Exception("Password not valid");

		final String token = jwtTokenUtil.generateToken(seller.getEmail());

		SellerSignupResponse sellerSignupResponse = new SellerSignupResponse(
				sellerFromRepo.getStore().getStoreslug(),
				token);

		return ResponseEntity.ok(sellerSignupResponse);
	}

	private void validateSellerLoginBody(Seller seller) throws Exception {
		if (Objects.isNull(seller.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(seller.getPassword()))
			throw new Exception("Password not found");
	}

	private void validateSellerSignupBody(SellerSignup sellerSignup) throws Exception {
		if (Objects.isNull(sellerSignup.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(sellerSignup.getPassword()))
			throw new Exception("Password not found");
		if (Objects.isNull(sellerSignup.getOtp()))
			throw new Exception("OTP not found");
		if (Objects.isNull(sellerSignup.getName()))
			throw new Exception("Name not found");
		if (Objects.isNull(sellerSignup.getStorename()))
			throw new Exception("Storename not found");
		if (Objects.isNull(sellerSignup.getStorelogo()))
			throw new Exception("Storelogo not found");
		if (Objects.isNull(sellerSignup.getStorebanner()))
			throw new Exception("Storebanner not found");
	}

	private String makeStorestlug(String storename) {
		String withoutspaceString = storename.replaceAll("\\s", "");
		String lowercaseaplhaString = withoutspaceString.replaceAll("[^a-zA-Z]", "").toLowerCase();
		return lowercaseaplhaString;
	}
}
