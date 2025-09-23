package com.example.journalApp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    EmailService emailService;

    @Test
    public void testSendMail(){
        emailService.sendEmail("meghamohata22@gmail.com", "Testing mail sender", "Is it working?");
    }
}