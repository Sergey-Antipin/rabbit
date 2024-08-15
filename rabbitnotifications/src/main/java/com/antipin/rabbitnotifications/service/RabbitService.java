package com.antipin.rabbitnotifications.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitService {

    @RabbitListener(queues = "new_likes")
    public void handleNewLike(String like) {
        System.out.println("New like: " + like);
    }

    @RabbitListener(queues = "new_comments")
    public void handleNewComment(String comment) {
        System.out.println("New comment: " + comment);
    }
}

