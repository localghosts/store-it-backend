package com.localghosts.storeit.config;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig implements Serializable {

	private static final long serialVersionUID = -2550185189626007488L;

	@Autowired
	@Value("${email.username}")
	private String emailUsername;

	@Value("${email.password}")
	private String emailPassword;

	private Properties emailConfiguration() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}

	public Session getSession() {
		String emailID = emailUsername + "@gmail.com";

		Properties props = emailConfiguration();
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailID, emailPassword);
			}
		});
		return session;
	}

	public String getSenderEmailAddress() {
		return String.format("store.it automation<%s@gmail.com>", emailUsername);
	}

	public String getContent() {
		return "This is an computer generated message.\n\n" + "Store IT Automation";
	}
}
