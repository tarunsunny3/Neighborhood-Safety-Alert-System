package com.neighborhood.NotificationService.controller;

import com.neighborhood.NotificationService.model.AlertMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/alert")
    @SendTo("/topic/alerts")
    public AlertMessage send(AlertMessage message) {
        return message;
    }
}
