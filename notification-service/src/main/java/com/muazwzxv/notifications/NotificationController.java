package com.muazwzxv.notifications;

import com.muazwzxv.clients.notifications.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void send(@RequestBody NotificationRequestDto req) {
        log.info("New Notification {}", req);
        notificationService.sendMessage(req);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return new ResponseEntity<>(this.notificationService.getAll(), HttpStatus.OK);
    }
}
