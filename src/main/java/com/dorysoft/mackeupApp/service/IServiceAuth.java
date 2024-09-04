package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.dto.LoginRequestDto;
import com.dorysoft.mackeupApp.dto.LoginResponseDto;
import com.dorysoft.mackeupApp.dto.PasswordResetCodeDto;
import com.dorysoft.mackeupApp.dto.PasswordResetRequestDto;

public interface IServiceAuth {
    LoginResponseDto login(LoginRequestDto loginRequest);
    void sendPasswordResetCode(String email);
    void resetPassword(PasswordResetCodeDto codeDto, PasswordResetRequestDto passwordResetRequest);
}