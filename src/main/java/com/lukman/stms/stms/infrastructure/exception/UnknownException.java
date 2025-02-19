package com.lukman.stms.stms.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownException extends RuntimeException {
    private String mesage;

    public String getMesage() {
        return mesage;
    }

    public UnknownException(String mesage) {
        super(mesage);
        this.mesage = mesage;
    }

    public UnknownException(String message, String mesage) {
        super(message);
        this.mesage = mesage;
    }

    public UnknownException(String message, Throwable cause, String mesage) {
        super(message, cause);
        this.mesage = mesage;
    }

}
