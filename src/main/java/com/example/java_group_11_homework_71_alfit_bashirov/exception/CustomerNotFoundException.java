package com.example.java_group_11_homework_71_alfit_bashirov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    private String message;

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        this.message = message;
    }
}
