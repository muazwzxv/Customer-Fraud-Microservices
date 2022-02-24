package com.muazwzxv.customer;

import com.muazwzxv.clients.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void register(@RequestBody CustomerRequestDto req) {
        log.info("New Customer Registration {}", req);
        customerService.registerCustomer(req);
    }

    @GetMapping
    public void getAll() {
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<CustomerDTO>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

}
