package com.muazwzxv.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notifications")
public interface NotificationClient {

    @PostMapping("/api/v1/notification")
    NotificationRequestDto sendMessage(@RequestBody NotificationRequestDto req);
}
