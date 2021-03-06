package com.muazwzxv.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FraudCheckRepository extends JpaRepository<FraudCheckHistory, Long> {
    Optional<FraudCheckHistory> findByUuid(UUID uuid);
}
