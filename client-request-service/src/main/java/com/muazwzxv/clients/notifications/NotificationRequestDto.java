package com.muazwzxv.clients.notifications;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationRequestDto {
    Long senderId;
    Long receiverId;
    String content;
}
