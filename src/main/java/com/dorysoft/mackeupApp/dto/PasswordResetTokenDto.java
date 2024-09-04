package com.dorysoft.mackeupApp.dto;

// DTO para la validación del token de recuperación de contraseña
public class PasswordResetTokenDto {
    private String token;

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}