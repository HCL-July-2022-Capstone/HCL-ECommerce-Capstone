package com.jordan.common;

import java.util.concurrent.CountDownLatch;

import com.jordan.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver { // responds to published messages

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    EmailService emailService;


    // method for receiving messages
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        this.emailService.sendInventoryStatustEmail
                (message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {

        return latch;
    }

}