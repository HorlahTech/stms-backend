package com.lukman.stms.stms.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    // @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    // public @ResponseBody ResponseEntity<ResponseErrorDetails>
    // handleParseType(HttpMessageNotReadableException ex) {
    // ResponseErrorDetails err = new ResponseErrorDetails(ex.getMessage(),
    // 500,
    // LocalDateTime.now());
    // return new ResponseEntity<ResponseErrorDetails>(err,
    // HttpStatus.INTERNAL_SERVER_ERROR);
    // }

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

    // @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @Override
    public ResponseEntity handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return ResponseEntity.badRequest().body(Map.of(
                "message", "Missing required parameter: " + ex.getParameterName(),
                "status", 400,
                "timeStamp", LocalDateTime.now()));
    }

}
