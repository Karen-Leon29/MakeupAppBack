package com.dorysoft.mackeupApp.dto;

// DTO para la respuesta de inicio de sesión
public class LoginResponseDto {
    private String message;
    private String rol;

    public LoginResponseDto(String message, String rol) {
        this.message = message;
        this.rol = rol;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}