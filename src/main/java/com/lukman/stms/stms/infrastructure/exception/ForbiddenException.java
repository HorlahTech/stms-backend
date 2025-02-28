package com.lukman.stms.stms.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private String message;

    public ForbiddenException(String message) {
        super(message);
        this.message = message;
    }

}
