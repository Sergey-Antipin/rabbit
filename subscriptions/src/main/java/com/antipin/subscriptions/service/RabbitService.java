package com.antipin.subscriptions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "new_publications")
    public void handleNewPublication(String publication) {
        System.out.println("Received publication: " + publication);
        String notification = "Notification for subscriber::" + publication;
        rabbitTemplate.convertAndSend("subscribers_notifications", notification);
    }
}
