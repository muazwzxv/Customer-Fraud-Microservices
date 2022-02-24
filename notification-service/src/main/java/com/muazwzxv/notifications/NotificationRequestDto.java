package com.muazwzxv.notifications;

public record NotificationRequestDto(
        Long senderId,
        Long receiverId,
        String content
) {
}
