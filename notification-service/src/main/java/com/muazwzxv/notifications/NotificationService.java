package com.muazwzxv.notifications;

import com.muazwzxv.clients.notifications.NotificationDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ModelMapper mapper;

    public List<NotificationDTO> getAll() {
        List<Notifications> notifications = this.notificationRepository.findAll();
        return notifications.stream()
                .map(this::notificationToDTO)
                .collect(Collectors.toList());
    }

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

    // Model mapper methods

    public NotificationDTO notificationToDTO(Notifications notifications) {
        return mapper.map(notifications, NotificationDTO.class);
    }
}
