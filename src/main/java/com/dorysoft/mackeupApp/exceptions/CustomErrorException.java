package com.dorysoft.mackeupApp.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomErrorException extends RuntimeException {

    private HttpStatus httpStatus;
    private ErrorResponse errorResponse;
}