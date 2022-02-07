package com.muazwzxv.customer;

import com.muazwzxv.clients.fraud.FraudCheckResponseDto;
import com.muazwzxv.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRequestDto customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // todo: check if email is valid & taken
        customerRepository.saveAndFlush(customer);

        // todo: check if fraudster
        FraudCheckResponseDto response = fraudClient.isFraudster(customer.getId());

        if (response != null)
            if (response.isFraud()) throw new IllegalStateException("Fraud detected");

        log.info(response.toString());

        // todo: send notification
    }
}
