package com.localghosts.storeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import com.localghosts.storeit.model.Buyer;
import com.localghosts.storeit.model.JwtResponse;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.repo.BuyerRepo;
import com.localghosts.storeit.repo.OTPRepo;
import com.localghosts.storeit.security.JwtTokenUtil;
import com.localghosts.storeit.model.BuyerSignup;

//The Buyer Signup and Login functionality.
@RestController
@CrossOrigin(origins = "*")
public class BuyerAuthController {

	@Autowired
	OTPRepo otpRepo;

	@Autowired
	BuyerRepo buyerRepo;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/buyer/signup")
	public ResponseEntity<JwtResponse> BuyerSignup(@RequestBody BuyerSignup signup) throws Exception {
		validateSignupBody(signup);

		String email = signup.getEmail();

		OTP otpentry = otpRepo.findByEmail(email);

		if (otpentry == null || otpentry.getUsed() == true)
			throw new Exception("OTP not found or already used");
		if (otpentry.getOtp().equals(signup.getOtp()) == false)
			throw new Exception("OTP not valid");
		
		//Encypt the user password and then save to database.
		String encryptedPassword = bCryptPasswordEncoder.encode(signup.getPassword());

		Buyer buyer = new Buyer(signup.getEmail(), signup.getName(), encryptedPassword);
		buyerRepo.save(buyer);

		otpentry.setUsed(true);
		otpRepo.save(otpentry);

		final String token = jwtTokenUtil.generateToken(buyer.getEmail());

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping("/buyer/login")
	public ResponseEntity<JwtResponse> BuyerLogin(@RequestBody Buyer buyer) throws Exception {
		validateBuyerLoginBody(buyer);

		Buyer buyerFromRepo = buyerRepo.findByEmail(buyer.getEmail());

		if (buyerFromRepo == null)
			throw new Exception("Buyer not found");

		//Compare the hash of the password.
		if (bCryptPasswordEncoder.matches(buyer.getPassword(), buyerFromRepo.getPassword()) == false)
			throw new Exception("Password not valid");

		final String token = jwtTokenUtil.generateToken(buyer.getEmail());

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	//Ensure that the object is not Null
	private void validateBuyerLoginBody(Buyer buyer) throws Exception {
		if (Objects.isNull(buyer.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(buyer.getPassword()))
			throw new Exception("Password not found");
	}

	private void validateSignupBody(BuyerSignup signup) throws Exception {
		if (Objects.isNull(signup.getEmail()))
			throw new Exception("Email not found");
		if (Objects.isNull(signup.getPassword()))
			throw new Exception("Password not found");
		if (Objects.isNull(signup.getOtp()))
			throw new Exception("OTP not found");
		if (Objects.isNull(signup.getName()))
			throw new Exception("Name not found");
	}
}
