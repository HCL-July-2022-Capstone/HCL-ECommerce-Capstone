package com.jordan.controller;

import com.jordan.common.UserConstants;
import com.jordan.model.Roles;
import com.jordan.model.User;
import com.jordan.repository.RoleRepository;
import com.jordan.repository.UserRepository;
import com.jordan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;
	private RoleRepository roleRepo;

	@Autowired
	private UserService service;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	Roles role;

	@PostMapping("/join")
	public String add(@RequestBody User user) {
		user.setRole(UserConstants.ADMIN_ACCESS);
				
		String encryptedPass = passwordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPass);

		repo.save(user);

		emailSent(user.getUsername());
		
		return "Hi " + user.getFirstName() + " Welcome to Group!";
	}
	
	@GetMapping("/getAllUsers")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}
	
	@GetMapping("/get/{id}")
	public Optional<User> getUserId(@PathVariable Integer id) {

		return service.getUserById(id);
	}

	@PutMapping("/update/{id}")
	public void UpdateProductById(@RequestBody User user, @PathVariable Integer id) {
		user.setRole(UserConstants.ADMIN_ACCESS);
		String encryptedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		repo.save(user);
	}

	@DeleteMapping("/get/{id}")
	public void deleteUser(@PathVariable Integer id) {

		service.deleteUser(id);
	}

	public void emailSent(String args) {

		System.out.println("Sending Email...");

//        try {
//            sendEmail();
//          //  sendEmailWithAttachment();
//
//        } catch (MessagingException | IOException e) {
//            e.printStackTrace();
//        }

		sendEmail(args);

		System.out.println("Done");

	}

	void sendEmail(String args) {
//		JavaMailSender javaMailSender = new JavaMailSender();

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(args);

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}

	void sendEmailWithAttachment() throws MessagingException, IOException {
//		JavaMailSender javaMailSender = new JavaMailSender();

		MimeMessage msg = javaMailSender.createMimeMessage();

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

		javaMailSender.send(msg);

	}

}
