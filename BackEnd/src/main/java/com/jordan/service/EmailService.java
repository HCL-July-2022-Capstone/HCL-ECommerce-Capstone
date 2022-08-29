package com.jordan.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jordan.model.Cart;
import com.jordan.model.Orders;
import com.jordan.model.User;

public class EmailService {
	
	private JavaMailSenderImpl mailSender;
	
	public EmailService() {
    	mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
          
        mailSender.setUsername("c45a8580e274fc");
        mailSender.setPassword("0393150e31cfd8");
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
	}

	public void sendRegistrationEmail(User user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getUsername());
		msg.setSubject("Welcome to our super cool ecommerce app!");
		msg.setText("Hello "+user.getFirstName()+", welcome!");
		mailSender.send(msg);
	}
	
	void sendConfirmationEmail(Orders order) {
		SimpleMailMessage msg = new SimpleMailMessage();
		User user = order.getUserOrder();
		msg.setTo(user.getUsername());
		msg.setSubject("Order #"+order.getOrderId());
		msg.setText("Your order has been placed. Total: "+order.getTotalPrice());
	}
	void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = mailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("1@gmail.com");

		helper.setSubject("Testing from Spring Boot");

		// default = text/plain
		// helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);

		// FileSystemResource file = new FileSystemResource(new
		// File("classpath:android.png"));

		// Resource resource = new ClassPathResource("android.png");
		// InputStream input = resource.getInputStream();

		// ResourceUtils.getFile("classpath:android.png");

		helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));

		mailSender.send(msg);

	}
}