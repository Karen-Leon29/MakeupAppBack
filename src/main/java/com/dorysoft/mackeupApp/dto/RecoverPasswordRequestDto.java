package com.dorysoft.mackeupApp.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RecoverPasswordRequestDto {
    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email no es válido")
    private String email;
}
