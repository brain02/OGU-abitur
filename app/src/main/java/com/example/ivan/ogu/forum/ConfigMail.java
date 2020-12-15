package com.example.ivan.ogu.forum;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfigMail {

	//final String emailPort = "465"; //"587";// gmail's smtp port
	final String smtpAuth = "true";
	final String starttls = "true";
	final String emailHost = "smtp.gmail.com";

	final String fromEmail =""; // от
	final String fromPassword ="";
	final String toEmail = "";  // получатель


	String emailSubject;
	String emailBody;

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;


	public ConfigMail (String emailSubject, String emailBody) {

		this.emailSubject = emailSubject;
		this.emailBody = emailBody;

		//emailProperties.put("mail.smtp.port", emailPort);
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.auth", smtpAuth);
		emailProperties.put("mail.smtp.starttls.enable", starttls);
	}

	public MimeMessage createEmailMessage() throws AddressException,
			MessagingException, UnsupportedEncodingException {

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));

		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));


		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html; charset=utf-8"); // for a html email
		//emailMessage.setText(emailBody);// for a text email

		return emailMessage;
	}

	public void sendEmail() throws AddressException, MessagingException {

		Transport transport = mailSession.getTransport("smtps");
			transport.connect(emailHost, fromEmail, fromPassword);
			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
			transport.close();
	}

}
