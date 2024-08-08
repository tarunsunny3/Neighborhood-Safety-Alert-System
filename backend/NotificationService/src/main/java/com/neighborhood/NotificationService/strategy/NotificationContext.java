package com.neighborhood.NotificationService.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationContext {

    private final Map<String, NotificationStrategy> strategies;

    @Autowired
    public NotificationContext(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public void executeStrategy(String type, String message) {
        NotificationStrategy strategy = strategies.get(type);
        if (strategy != null) {
            strategy.sendNotification(message);
        } else {
            throw new IllegalArgumentException("Unknown strategy type: " + type);
        }
    }
}
