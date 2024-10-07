package com.dorysoft.mackeupApp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetailResponse {

    private String field;
    private String message;
}