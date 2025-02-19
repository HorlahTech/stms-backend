package com.lukman.stms.stms.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.CONFLICT)
@AllArgsConstructor
@Getter
@Setter
public class ConflictException extends RuntimeException {
    private String message;

}
