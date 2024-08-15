package com.antipin.publications.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PublicationController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/publications")
    public ResponseEntity<String> newPublication(@RequestBody String pub) {
        rabbitTemplate.convertAndSend("new_publications", pub);
        return ResponseEntity.ok(pub);
    }

    @PostMapping("/like")
    public ResponseEntity<String> newLike(@RequestBody String like) {
        rabbitTemplate.convertAndSend("notifications_exchange", "new.likes.user123", like);
        return ResponseEntity.ok(like);
    }

    @PostMapping("/comment")
    public ResponseEntity<String> newComment(@RequestBody String comment) {
        rabbitTemplate.convertAndSend("notifications_exchange", "new.comments.user123", comment);
        return ResponseEntity.ok(comment);
    }
}
