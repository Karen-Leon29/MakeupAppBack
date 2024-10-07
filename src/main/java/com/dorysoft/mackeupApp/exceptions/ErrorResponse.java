package com.dorysoft.mackeupApp.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String code;
    private String message;
    private List<ErrorDetailResponse> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

}