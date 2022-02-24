package com.muazwzxv.clients.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    UUID uuid;
    String firstName;
    String lastName;
    String email;
}
