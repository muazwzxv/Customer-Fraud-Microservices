package com.muazwzxv.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMqProducer {

    private final AmqpTemplate template;

    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routing key {}. Payload {} ", exchange, routingKey, payload);
        this.template.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing key {}. Payload {} ", exchange, routingKey, payload);
    }
}
