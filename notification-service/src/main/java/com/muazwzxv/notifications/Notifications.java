package com.muazwzxv.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notifications {
    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Long id;
    private UUID uuid;
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime createdAt;

    @PrePersist
    private void setUUID() {
        this.uuid = UUID.randomUUID();
    }
}
