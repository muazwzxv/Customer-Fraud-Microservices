package com.muazwzxv.notifications;

public record NotificationRequestDto(
        Integer senderId,
        Integer receiverId,
        String content
) {
}
