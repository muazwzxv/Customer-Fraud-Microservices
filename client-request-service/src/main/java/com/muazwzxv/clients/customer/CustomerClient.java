package com.muazwzxv.clients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("api/v1/customers/{customerId}")
    CustomerDTO getById(@PathVariable("customerId") Long id);

    @GetMapping("api/v1/customers/internal/{email}")
    Optional<CustomerDTO> getByEmail(@PathVariable(name = "email") String email);

}
