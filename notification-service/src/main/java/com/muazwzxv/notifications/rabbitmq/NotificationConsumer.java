package com.muazwzxv.notifications.rabbitmq;

import com.muazwzxv.notifications.NotificationRequestDto;
import com.muazwzxv.notifications.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequestDto req) {
        log.info("Consume {} from queue", req);
        notificationService.sendMessage(req);
    }
}
