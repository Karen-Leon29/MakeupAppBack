package com.dorysoft.mackeupApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetCodeDto {
    private String code;
}
