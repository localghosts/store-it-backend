package com.localghosts.storeit.controller;

import java.io.IOException;
import java.util.Objects;

import javax.mail.MessagingException;

import com.localghosts.storeit.model.EmailRequest;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.repo.OTPRepo;
import com.localghosts.storeit.service.EmailingService;
import com.localghosts.storeit.service.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class OTPController {
	@Autowired
	OTPRepo otpRepo;

	@Autowired
	private EmailingService emailingService;

	@Autowired
	private RandomString rnd;

	@PostMapping("/otp")
	public String getOTP(@RequestBody OTP OTPRequest) throws MessagingException, IOException {
		if (Objects.isNull(OTPRequest.getEmail()) || OTPRequest.getEmail().isEmpty())
			throw new Error("Please provide a valid email address");

		String email = OTPRequest.getEmail();

		if (email == null)
			throw new Error("Email is null");

		if (otpRepo.findByEmail(email) != null)
			throw new Error("OTP Already Sent");

		OTP OTPEntry = new OTP(rnd.generateOTP(6), email, false);

		EmailRequest emailRequest = new EmailRequest();

		emailRequest.setReceiverEmail(email);
		emailRequest.setSubject("OTP");

		String message = "Your OTP: " + OTPEntry.getOtp();
		emailRequest.setMessage(message);

		emailingService.SendMail(emailRequest);

		otpRepo.save(OTPEntry);

		return "OTP Sent";
	}

}
