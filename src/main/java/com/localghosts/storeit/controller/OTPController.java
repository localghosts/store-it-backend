package com.localghosts.storeit.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.localghosts.storeit.config.OTPRepo;
import com.localghosts.storeit.model.EmailRequest;
import com.localghosts.storeit.model.OTP;
import com.localghosts.storeit.model.RandomString;
import com.localghosts.storeit.service.EmailingService;

@RestController
public class OTPController {
	@Autowired
	OTPRepo repo;
	
	@Autowired
	private EmailingService emailingService;
	
	@Autowired
	private RandomString rnd;
	
	@PostMapping( "/OTP" )
	public String getOTP(@RequestBody String email) throws  MessagingException, IOException
	{
		System.out.println(email);
		if(repo.findByEmail(email) != null) 
			return "OTP Already Sent";
		OTP OTPEntry = new OTP(rnd.getAlphaNumericString(6), email, 0);
		EmailRequest emailRequest = new EmailRequest();

		emailRequest.setReceiverEmail(email);
		emailRequest.setSubject("OTP");

		String message = OTPEntry.getOtp();
		emailRequest.setMessage(message);

		emailingService.SendMail(emailRequest);
		repo.save(OTPEntry);
		return "OTP Sent";
	}
	
	
}
