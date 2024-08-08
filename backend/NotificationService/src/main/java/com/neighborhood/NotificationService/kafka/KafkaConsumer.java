package com.neighborhood.NotificationService.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.neighborhood.NotificationService.strategy.NotificationContext;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private NotificationContext notificationContext;

    @KafkaListener(topics = "incident-alerts", groupId = "group_id")
    public void consume(String message) {
        log.info("Received message from Kafka broker {}", message);
        notificationContext.executeStrategy("websocket", message);
//        notificationContext.executeStrategy("email", message);
        // Add other strategies as needed
    }
}

