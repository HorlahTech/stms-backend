package com.lukman.stms.stms.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuccessResponse<T> {
    private String message;
    private int status;
    private T data;

    public SuccessResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
