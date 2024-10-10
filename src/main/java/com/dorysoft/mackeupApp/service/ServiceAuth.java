package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.PasswordResetToken;
import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.*;
import com.dorysoft.mackeupApp.repository.IRepositoryUser;
import com.dorysoft.mackeupApp.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceAuth implements IServiceAuth {

    @Autowired
    private IRepositoryUser iRepositoryUser;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    */

    @Autowired
    private IServiceEmail serviceEmail;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        User user = iRepositoryUser.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos"));

        if (loginRequest.getPassword().equals(user.getPassword())) {
            //return new LoginResponseDto("Sesión iniciada", user.getRol());
            return null;
        } else {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }
    }

    @Override
    public void sendPasswordResetCode(String email) {
        User user = iRepositoryUser.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("El email no se encuentra registrado"));

        // Generar un código de 6 dígitos
        String resetCode = String.format("%06d", new Random().nextInt(999999));

        // Guardar el código en la base de datos junto con su fecha de expiración
        PasswordResetToken passwordResetToken = new PasswordResetToken(resetCode, user);
        passwordResetTokenRepository.save(passwordResetToken);

        // Enviar el código por correo electrónico
        String subject = "Recuperación de contraseña";
        String message = "Su código de recuperación de contraseña es: " + resetCode;

        serviceEmail.sendEmail(user.getEmail(), subject, message);
    }

    @Override
    public void resetPassword(PasswordResetCodeDto codeDto, PasswordResetRequestDto passwordResetRequest) {
        // Buscar el token en la base de datos por el código
        PasswordResetToken token = passwordResetTokenRepository.findByCode(codeDto.getCode())
                .orElseThrow(() -> new IllegalArgumentException("Código no válido"));

        // Verificar si el código ha expirado
        if (token.isExpired()) {
            throw new IllegalArgumentException("Código ha expirado");
        }

        // Buscar al usuario asociado con el código
        User user = token.getUser();

        // Resetear la contraseña
        user.setPassword(passwordResetRequest.getNewPassword());
       // user.setPassword(passwordEncoder.encode(passwordResetRequest.getNewPassword()));
        iRepositoryUser.save(user);

        // Eliminar el token después de su uso
        passwordResetTokenRepository.delete(token);
    }
}