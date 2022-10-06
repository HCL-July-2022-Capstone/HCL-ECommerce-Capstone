package com.jordan.service;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.jordan.model.Orders;

public class EmailService
{
	private JavaMailSenderImpl mailSender;

	public EmailService()
	{
		mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.mailtrap.io");
		mailSender.setPort(2525);

		mailSender.setUsername("3f7f37bd18593f");
		mailSender.setPassword("0da7c77f5e5826");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
	}

	public void sendRegistrationEmail(String user)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user);
		msg.setSubject("Welcome to our super cool ecommerce app!");
		msg.setText("Hello " + user + ", welcome!");
		mailSender.send(msg);
	}

	public void sendConfirmationEmail(String user, Orders order)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user);
		msg.setSubject("Order #" + order.getOrderId());
		msg.setText("Your order has been placed. Total: " + order.getTotalPrice());
		mailSender.send(msg);
	}

//	public void sendEmailWithAttachment() throws MessagingException, IOException
//	{
//		MimeMessage msg = mailSender.createMimeMessage();
//
//		// true = multipart message
//		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//		helper.setTo("1@gmail.com");
//
//		helper.setSubject("Testing from Spring Boot");
//
//		// default = text/plain
//		// helper.setText("Check attachment for image!");
//
//		// true = text/html
//		helper.setText("<h1>Check attachment for image!</h1>", true);
//
//		helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));
//
//		mailSender.send(msg);
//	}

	public void sendInventoryStatustEmail(String prod)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("your.techshop@techshop.com");
		msg.setTo("admin@gmail.com");
		msg.setSubject("Inventory Status");
		msg.setText(prod);
		mailSender.send(msg);
	}
}