package com.muazwzxv.clients.customer;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    UUID uuid;
    String firstName;
    String lastName;
    String email;
}
