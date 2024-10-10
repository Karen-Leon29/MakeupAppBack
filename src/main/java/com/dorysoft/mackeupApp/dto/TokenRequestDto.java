package com.dorysoft.mackeupApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenRequestDto {
    @NotBlank(message = "Token es obligatorio")
    private String token;
}
