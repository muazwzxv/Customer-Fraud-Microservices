package com.muazwzxv.notifications;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notifications, Integer> {
    Optional<Notifications> findByUuid(UUID uuid);
}
