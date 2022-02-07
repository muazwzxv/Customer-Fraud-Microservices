package com.muazwzxv.clients.notifications;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationRequestDto {
    Integer senderId;
    Integer receiverId;
    String content;
}
