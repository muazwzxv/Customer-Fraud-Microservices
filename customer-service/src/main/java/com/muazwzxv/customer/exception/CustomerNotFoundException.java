package com.muazwzxv.customer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class CustomerNotFoundException extends RuntimeException {
    private final String fieldName;
    private final Object fieldValue;

    public CustomerNotFoundException(String fieldName, Object fieldValue) {
        super(String.format("Customer not found with %s: '%s'", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
