package com.dorysoft.mackeupApp.validations;

import com.dorysoft.mackeupApp.dto.LoginRequestDto;
import com.dorysoft.mackeupApp.dto.RecoverPasswordRequestDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RecoverPasswordValidator {
    public Map<String, List<String>> validate(RecoverPasswordRequestDto user) {
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

        return errors;
    }
}
