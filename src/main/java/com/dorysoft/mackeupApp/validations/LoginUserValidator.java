package com.dorysoft.mackeupApp.validations;

import com.dorysoft.mackeupApp.dto.LoginRequestDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginUserValidator {
    public Map<String, List<String>> validate(LoginRequestDto user) {
        Map<String, List<String>> errors = new HashMap<>();

        // Validación de Email
        List<String> emailErrors = new ArrayList<>();
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            emailErrors.add("Email es obligatorio.");
        }
        if (user.getEmail() != null && user.getEmail().length() > 30) {
            emailErrors.add("Email no debe exceder los 30 caracteres.");
        }
        if (user.getEmail() != null && !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", user.getEmail())) {
            emailErrors.add("Email no tiene un formato válido.");
        }
        if (!emailErrors.isEmpty()) {
            errors.put("email", emailErrors);
        }

        // Validación de Contraseña
        List<String> passwordErrors = new ArrayList<>();
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            passwordErrors.add("Contraseña es obligatoria.");
        }
        if (!passwordErrors.isEmpty()) {
            errors.put("password", passwordErrors);
        }

        return errors;
    }
}
