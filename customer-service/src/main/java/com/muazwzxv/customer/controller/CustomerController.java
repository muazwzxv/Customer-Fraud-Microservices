package com.muazwzxv.customer.controller;

import com.muazwzxv.clients.customer.CustomerDTO;
import com.muazwzxv.customer.CustomerRequestDto;
import com.muazwzxv.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

//    @PostMapping
//    public void register(@RequestBody CustomerRequestDto req) {
//        log.info("New Customer Registration {}", req);
//        customerService.registerCustomer(req);
//    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> postUser(@RequestBody CustomerRequestDto req) {
        return new ResponseEntity<>(this.customerService.createCustomer(req), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return new ResponseEntity<>(this.customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/internal/{email}")
    public ResponseEntity<CustomerDTO> getByEmail(@PathVariable(name = "email") String email) {
        return new ResponseEntity<>(this.customerService.getCustomerByEmail(email), HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<CustomerDTO> getByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return new ResponseEntity<>(this.customerService.getCustomerByUuid(uuid), HttpStatus.OK);
    }
}
