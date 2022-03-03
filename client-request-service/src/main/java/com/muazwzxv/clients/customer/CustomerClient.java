package com.muazwzxv.clients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("api/v1/customers/{customerId}")
    CustomerDTO getCustomerById(@PathVariable("customerId") Long id);
}
