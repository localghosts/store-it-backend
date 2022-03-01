package com.localghosts.storeit.service;

import java.io.IOException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.localghosts.storeit.config.EmailConfig;
import com.localghosts.storeit.model.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailingService {

	@Autowired
	private EmailConfig emailConfig;

	public void SendMail(EmailRequest emailRequest) throws AddressException, MessagingException, IOException {

		Session session = emailConfig.getSession();

		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(emailConfig.getSenderEmailAddress(), false));
		msg.setContent(emailConfig.getContent(), "text/html");
		msg.setSentDate(new Date());

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getReceiverEmail()));
		msg.setSubject(emailRequest.getSubject());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(emailRequest.getMessage(), "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		Transport.send(msg);
	}





}
