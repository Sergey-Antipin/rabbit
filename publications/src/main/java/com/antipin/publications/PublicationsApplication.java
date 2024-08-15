package com.antipin.publications;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicationsApplication.class, args);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("notifications_exchange", false, false);
    }

    @Bean
    public Queue newPublicationsQueue() {
        return new Queue("new_publications", false);
    }

    @Bean
    public Queue subscribersNotificationsQueue() {
        return new Queue("subscribers_notifications", false);
    }

    @Bean
    public Queue activityLikeQueue() {
        return new Queue("new_likes", false);
    }

    @Bean
    public Queue activityCommentQueue() {
        return new Queue("new_comments", false);
    }

    @Bean
    public Binding bindingNewComments(Queue activityCommentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(activityCommentQueue).to(exchange).with("new.comments.#");
    }

    @Bean
    public Binding bindingNewLikeQueue(Queue activityLikeQueue, TopicExchange exchange) {
        return BindingBuilder.bind(activityLikeQueue).to(exchange).with("new.likes.#");
    }
}
