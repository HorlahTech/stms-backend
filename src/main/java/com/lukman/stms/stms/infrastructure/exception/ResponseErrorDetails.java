package com.lukman.stms.stms.infrastructure.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseErrorDetails {
    private String message;
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    @Override
    public String toString() {
        return "ResponseErrorDetails [message=" + message + ", status=" + status + ", time=" + time + "]";
    }

}
