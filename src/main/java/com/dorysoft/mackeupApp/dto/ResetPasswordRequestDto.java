package com.dorysoft.mackeupApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ResetPasswordRequestDto {
    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email no es válido")
    private String email;

    @NotBlank(message = "Contraseña es obligatoria")
    private String password;

    @NotBlank(message = "Confirmación de contraseña es obligatoria")
    private String confirmPassword;

    @NotBlank(message = "Código de recuperación es obligatorio")
    private String code;
}
