package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRepositoryUser extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneOrEmail(String phone, String email);
}
