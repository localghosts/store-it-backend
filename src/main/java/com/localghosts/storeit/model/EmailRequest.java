package com.localghosts.storeit.model;

import java.io.Serializable;

public class EmailRequest implements Serializable {

	private static final long serialVersionUID = -2550185189626007488L;

	private String receiverEmail;
	private String subject;
	private String message;

	public EmailRequest() {
	}

	public EmailRequest(String receiverEmail, String subject, String message) {
		this.receiverEmail = receiverEmail;
		this.subject = subject;
		this.message = message;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getSubject() {
		return subject + " | store.it";
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message + "<br><br>Best<br>store-it team <br><br> "
				+ "<small>This is a system generated email. Please do not reply to this email.</small>";
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
