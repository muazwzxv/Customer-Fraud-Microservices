package com.muazwzxv.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // todo: check if email is valid & taken
        // todo: store to db

    }
}
