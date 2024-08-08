package com.neighborhood.NotificationService.strategy.impl;



import com.neighborhood.NotificationService.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service("websocket")
public class WebSocketNotificationStrategyImpl implements NotificationStrategy {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendNotification(String message) {
        template.convertAndSend("/topic/alerts", message);
    }
}
