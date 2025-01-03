package com.quotegenerator.demo;

import com.quotegenerator.demo.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        String to = "ayushmanjena24@gmail.com";
        String subject = "Quote Generator";
        String body = "test mail";
        //EmailService emailService = new EmailService();
        emailService.sendEmail(to, subject, body);
    }
}
