package com.lukman.stms.stms.infrastructure.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AllCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public @ResponseBody ResponseEntity<ResponseErrorDetails> generalException(Exception ex, WebRequest request) {
        ResponseErrorDetails err = new ResponseErrorDetails(ex.getMessage(),
                500,
                LocalDateTime.now());
        return new ResponseEntity<ResponseErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<ResponseErrorDetails> notFoundError(Exception ex, WebRequest request) {
        ResponseErrorDetails err = new ResponseErrorDetails(ex.getMessage(), 404,
                LocalDateTime.now());
        return new ResponseEntity<ResponseErrorDetails>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ConflictException.class })
    public ResponseEntity<ResponseErrorDetails> conflictException(Exception ex, WebRequest request) {
        ResponseErrorDetails err = new ResponseErrorDetails(ex.getMessage(), 409,
                LocalDateTime.now());
        return new ResponseEntity<ResponseErrorDetails>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { EmptyFieldException.class })
    public ResponseEntity<ResponseErrorDetails> empttField(Exception ex, WebRequest request) {
        ResponseErrorDetails err = new ResponseErrorDetails(ex.getMessage(), 400,
                LocalDateTime.now());
        return new ResponseEntity<ResponseErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

}
