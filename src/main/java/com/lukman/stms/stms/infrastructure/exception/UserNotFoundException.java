package com.lukman.stms.stms.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private String mesage;

    public String getMesage() {
        return mesage;
    }

    public UserNotFoundException(String mesage) {
        super(mesage);
        this.mesage = mesage;
    }

    public UserNotFoundException(String message, String mesage) {
        super(message);
        this.mesage = mesage;
    }

    public UserNotFoundException(String message, Throwable cause, String mesage) {
        super(message, cause);
        this.mesage = mesage;
    }

}
