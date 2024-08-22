package com.dorysoft.mackeupApp.dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank(message = "Nombre es obligatorio")
    @Size(max = 100, message = "Nombre no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nombre solo debe contener letras")
    private String name;

    @NotBlank(message = "Apellido es obligatorio")
    @Size(max = 100, message = "Apellido no debe exceder los 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Apellido solo debe contener letras")
    private String lastName;

    @NotBlank(message = "Teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10,14}$", message = "Teléfono debe contener entre 10 y 14 dígitos")
    private String phone;

    @NotBlank(message = "Dirección es obligatoria")
    @Size(max = 100, message = "Dirección no debe exceder los 100 caracteres")
    private String address;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email no es válido")
    private String email;

    @NotBlank(message = "Confirmación de email es obligatoria")
    private String confirmEmail;

    @NotBlank(message = "Contraseña es obligatoria")
    private String password;

    @NotBlank(message = "Confirmación de contraseña es obligatoria")
    private String confirmPassword;
}
