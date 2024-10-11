package com.dorysoft.mackeupApp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {
    private String code;
    private String message;
    private T data;

    public SuccessResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public SuccessResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}