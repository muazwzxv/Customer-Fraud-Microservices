package com.muazwzxv.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;

    public boolean isFraudulentCustomer(Long customerId) {
        // We'll say everyone is not a fraud right now
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .isFraud(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }
}
