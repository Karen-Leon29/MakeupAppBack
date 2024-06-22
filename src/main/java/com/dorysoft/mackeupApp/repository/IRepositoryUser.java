package com.dorysoft.mackeupApp.repository;

import com.dorysoft.mackeupApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryUser extends JpaRepository<User, Long> {
}
