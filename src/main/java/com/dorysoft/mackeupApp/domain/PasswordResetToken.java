package com.dorysoft.mackeupApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expirationDate;

    public PasswordResetToken(String code, User user) {
        this.code = code;
        this.user = user;
        // Establecer la fecha de expiración a 24 horas desde la creación
        this.expirationDate = LocalDateTime.now().plusHours(24);
    }

    // Método para verificar si el código ha expirado
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
