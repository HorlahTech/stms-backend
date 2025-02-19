package com.lukman.stms.stms.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmptyFieldException extends RuntimeException {
    private String mesage;

    public String getMesage() {
        return mesage;
    }

    public EmptyFieldException(String mesage) {
        super(mesage);
        this.mesage = mesage;
    }

    public EmptyFieldException(String message, String mesage) {
        super(message);
        this.mesage = mesage;
    }

    public EmptyFieldException(String message, Throwable cause, String mesage) {
        super(message, cause);
        this.mesage = mesage;
    }

}
