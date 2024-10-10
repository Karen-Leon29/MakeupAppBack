package com.dorysoft.mackeupApp.dto;

import com.dorysoft.mackeupApp.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private User user;
}