package com.muazwzxv.fraud;

import com.muazwzxv.clients.fraud.FraudCheckResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponseDto isFraud(@PathVariable(name = "customerId") Long id) {
        boolean isFraud = fraudCheckService.isFraudulentCustomer(id);

        log.info("Fraud check for customer {}", id);
        return new FraudCheckResponseDto(isFraud);
    }
}
