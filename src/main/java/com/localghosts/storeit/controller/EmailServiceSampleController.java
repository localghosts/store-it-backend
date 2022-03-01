package com.localghosts.storeit.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.localghosts.storeit.model.EmailRequest;
import com.localghosts.storeit.service.EmailingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class EmailServiceSampleController {

	@Autowired
	private EmailingService emailingService;

	@RequestMapping({ "/mail" })
	public String helloWorld() throws AddressException, MessagingException, IOException {
		EmailRequest emailRequest = new EmailRequest();

		emailRequest.setReceiverEmail("iitkcoin@gmail.com");
		emailRequest.setSubject("Test Mail");

		String message = "Dear User" + "<br><br>" + "This is a sample email to test the email service.";
		emailRequest.setMessage(message);

		emailingService.SendMail(emailRequest);
		return "Email Sent";
	}

}
