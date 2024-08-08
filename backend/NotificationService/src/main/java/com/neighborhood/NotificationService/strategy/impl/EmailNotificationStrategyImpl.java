package com.neighborhood.NotificationService.strategy.impl;



import com.neighborhood.NotificationService.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotificationStrategyImpl implements NotificationStrategy {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendNotification(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("tarunsunny2662@example.com");
        mailMessage.setSubject("Alert");
        mailMessage.setText(message);
        emailSender.send(mailMessage);
    }
}
