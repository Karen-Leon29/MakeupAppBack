package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.PasswordResetToken;
import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.repository.IRepositoryUser;
import com.dorysoft.mackeupApp.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePasswordResetToken implements IServicePasswordResetToken {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private IRepositoryUser iRepositoryUser;

    public void createPasswordResetTokenForUser(User user, String code) {
        PasswordResetToken passwordResetToken = this.passwordResetTokenRepository.findByUserId(user.getId()).orElse(null);

        if (passwordResetToken != null) {
            passwordResetToken.setCode(code);
            passwordResetTokenRepository.save(passwordResetToken);
        } else {
            passwordResetToken = new PasswordResetToken(code,user);
            passwordResetTokenRepository.save(passwordResetToken);
        }

        //TODO: Enviar correo electrónico
    }

    public String changeUserPassword(User user, String password, String code) {
        PasswordResetToken passwordResetToken = this.passwordResetTokenRepository.findByUserId(user.getId()).orElse(null);

        if(passwordResetToken == null) {
            return "notfound";
        }

        if (passwordResetToken.isExpired()) {
            return "expired";
        }

        if (!passwordResetToken.getCode().equals(code)) {
            return "code";
        }

        user.setPassword(password);
        passwordResetTokenRepository.delete(passwordResetToken);

        iRepositoryUser.save(user);
        return "success";

    }
}
