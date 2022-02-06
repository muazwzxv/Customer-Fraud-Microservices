package com.muazwzxv.fraud;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    public FraudCheckResponseDto isFraud(@PathVariable(name = "customerId") Integer id) {
        boolean isFraud = fraudCheckService.isFraudulentCustomer(id);

        return new FraudCheckResponseDto(isFraud);
    }
}
