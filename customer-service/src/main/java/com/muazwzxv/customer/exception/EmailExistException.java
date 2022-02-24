package com.muazwzxv.customer.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class EmailExistException extends RuntimeException {
    private final String email;

    public EmailExistException(String email) {
        super(String.format("Customer with email %s already exist", email));
        this.email = email;
    }
}
