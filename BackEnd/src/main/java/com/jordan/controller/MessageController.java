package com.jordan.controller;

import com.jordan.model.Product;
import com.jordan.repository.ProductRepository;
import com.jordan.service.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class MessageController {

    @Autowired
    ProductRepository repo;
    @Autowired
    EmailService emailService;

    private JavaMailSenderImpl mailSender;

    private final RabbitTemplate rabbitTemplate;

    static final String topicExchangeName = "inventory-exchange";

    private MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void inventory() {

        List<Product> lowStock = repo.findByQuantityOnHandLessThan(10);

        lowStock.forEach((product1)-> {
                    rabbitTemplate.convertAndSend(topicExchangeName,
                            "foo.bar.#",
                             "Only " + product1.getQuantityOnHand() + " " +
                                     product1.getProductName() + " left in inventory!");

                    System.out.println("Message sent successfully!");

        });
    }
    
    public void orderPlaced() {
    	rabbitTemplate.convertAndSend(topicExchangeName,
              "foo.bar.#",
               "Your order has been placed!");

      
    }
    
}