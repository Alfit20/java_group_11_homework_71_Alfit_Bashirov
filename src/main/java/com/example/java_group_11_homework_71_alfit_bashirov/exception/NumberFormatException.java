package com.example.java_group_11_homework_71_alfit_bashirov.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class NumberFormatException extends IllegalArgumentException {
    private String message;

    public NumberFormatException(String s, String message) {
        super(s);
        this.message = message;
    }
}
