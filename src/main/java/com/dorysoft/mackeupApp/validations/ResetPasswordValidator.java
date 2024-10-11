package com.dorysoft.mackeupApp.validations;

import com.dorysoft.mackeupApp.dto.ResetPasswordRequestDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ResetPasswordValidator {
    public Map<String, List<String>> validate(ResetPasswordRequestDto user) {
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
        if (user.getPassword() != null && (user.getPassword().length() < 8 || user.getPassword().length() > 16)) {
            passwordErrors.add("Contraseña debe tener entre 8 y 16 caracteres.");
        }
        if (user.getPassword() != null && !Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", user.getPassword())) {
            passwordErrors.add("Contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.");
        }
        if (!passwordErrors.isEmpty()) {
            errors.put("password", passwordErrors);
        }

        // Validación de Confirmación de Contraseña
        List<String> confirmPasswordErrors = new ArrayList<>();
        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            confirmPasswordErrors.add("Confirmación de contraseña es obligatoria.");
        }
        if (user.getConfirmPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
            confirmPasswordErrors.add("La confirmación de la contraseña no coincide.");
        }
        if (!confirmPasswordErrors.isEmpty()) {
            errors.put("confirmPassword", confirmPasswordErrors);
        }

        //Validacion de codigo de verificacion
        List<String> verificationCodeErrors = new ArrayList<>();
        if (user.getCode() == null || user.getCode().isEmpty()) {
            verificationCodeErrors.add("Codigo de verificacion es obligatorio.");
        }

        if (!verificationCodeErrors.isEmpty()) {
            errors.put("code", verificationCodeErrors);
        }

        return errors;
    }
}
