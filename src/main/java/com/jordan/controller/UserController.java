package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.model.User;
import com.jordan.model.UserRoles;
import com.jordan.repository.UserRepository;
import com.jordan.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	
	
//	@GetMapping("/")
//	public String Home() {
//		return "Home";
//	}
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		//user.setRole("ADMIN");
		String encryptedPass = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encryptedPass);
		
		repo.save(user);
		
		emailSent(user.getUsername());
		return "Hi " + user.getFirstName() + " Welcome to Group!";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		//user.setRole("ADMIN");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		
		Optional<User> temp = repo.findByUsername(user.getUsername());
		
		if(temp.isPresent()) {
				
		}
		
		encoder.matches(service.getPassword(), user.getPassword()); 
		repo.save(user);
		return "Hi " + user.getFirstName() + " Welcome to Group!";
	}
	
	@GetMapping("/getAll")
	public List<User> listallusers() {
		return service.getAllUsers();
	}

	@GetMapping("/get/{id}")
	public Optional<User> getEmpId(@PathVariable Integer id) {
		Optional<User> user = service.getUserById(id);
		return service.getUserById(id);
	}
	
//	@PutMapping("/users/{id}")
//	public void updateUser(@PathVariable Integer id) {
//		Optional<User> user = service.getUserById(id);
//		repo.save(user);
//	}
	
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
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        sendEmail(args);

        System.out.println("Done");

    }

    void sendEmail(String args) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(args);

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("my_photo.png", new ClassPathResource("ms1.png"));

        javaMailSender.send(msg);

    }


}
