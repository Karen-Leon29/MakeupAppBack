package com.dorysoft.mackeupApp.validations;

import com.dorysoft.mackeupApp.dto.UserRegistrationDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UserRegistrationValidator {

    public Map<String, List<String>> validate(UserRegistrationDto user) {
        Map<String, List<String>> errors = new HashMap<>();

        // Validación de Nombre
        List<String> nameErrors = new ArrayList<>();
        if (user.getName() == null || user.getName().isEmpty()) {
            nameErrors.add("Nombre es obligatorio.");
        }
        if (user.getName() != null && user.getName().length() > 100) {
            nameErrors.add("Nombre no debe exceder los 100 caracteres.");
        }
        if (user.getName() != null && !Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", user.getName())) {
            nameErrors.add("Nombre solo debe contener letras y espacios.");
        }
        if (!nameErrors.isEmpty()) {
            errors.put("name", nameErrors);
        }

        // Validación de Apellido
        List<String> lastNameErrors = new ArrayList<>();
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            lastNameErrors.add("Apellido es obligatorio.");
        }
        if (user.getLastName() != null && user.getLastName().length() > 100) {
            lastNameErrors.add("Apellido no debe exceder los 100 caracteres.");
        }
        if (user.getLastName() != null && !Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", user.getLastName())) {
            lastNameErrors.add("Apellido solo debe contener letras y espacios.");
        }
        if (!lastNameErrors.isEmpty()) {
            errors.put("lastName", lastNameErrors);
        }

        // Validación de Teléfono
        List<String> phoneErrors = new ArrayList<>();
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            phoneErrors.add("Teléfono es obligatorio.");
        }
        if (user.getPhone() != null && !Pattern.matches("^[0-9]{10,14}$", user.getPhone())) {
            phoneErrors.add("Teléfono debe contener entre 10 y 14 dígitos.");
        }
        if (!phoneErrors.isEmpty()) {
            errors.put("phone", phoneErrors);
        }

        // Validación de Dirección
        List<String> addressErrors = new ArrayList<>();
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            addressErrors.add("Dirección es obligatoria.");
        }
        if (user.getAddress() != null && user.getAddress().length() > 100) {
            addressErrors.add("Dirección no debe exceder los 100 caracteres.");
        }
        if (!addressErrors.isEmpty()) {
            errors.put("address", addressErrors);
        }

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

        // Validación de Confirmación de Email
        List<String> confirmEmailErrors = new ArrayList<>();
        if (user.getConfirmEmail() == null || user.getConfirmEmail().isEmpty()) {
            confirmEmailErrors.add("Confirmación de email es obligatoria.");
        }
        if (user.getConfirmEmail() != null && !user.getEmail().equals(user.getConfirmEmail())) {
            confirmEmailErrors.add("El email de confirmación no coincide.");
        }
        if (!confirmEmailErrors.isEmpty()) {
            errors.put("confirmEmail", confirmEmailErrors);
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

        return errors;
    }
}
