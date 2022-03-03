package com.muazwzxv.clients.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    UUID uuid;
    String firstName;
    String lastName;
    String password;
    String email;
}
