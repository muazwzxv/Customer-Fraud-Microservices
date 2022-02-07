package com.muazwzxv.notifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendMessage(NotificationRequestDto notificationRequest) {
        Notifications notification = Notifications.builder()
                .senderId(notificationRequest.senderId())
                .receiverId(notificationRequest.receiverId())
                .content(notificationRequest.content())
                .createdAt(LocalDateTime.now())
                .build();

        // todo: check if receiver and sender exist ?
        //  Or this service should only be concern of sending notifications

        notificationRepository.saveAndFlush(notification);

        log.info("Message sent {}", notification);
    }
}
