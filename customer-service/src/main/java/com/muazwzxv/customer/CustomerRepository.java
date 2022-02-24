package com.muazwzxv.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUuid(UUID uuid);


}
