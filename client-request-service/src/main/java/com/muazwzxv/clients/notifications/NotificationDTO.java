package com.muazwzxv.clients.notifications;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationDTO {
    UUID uuid;
    Long senderId;
    Long receiverId;
    String content;
    LocalDateTime createdAt;
}
