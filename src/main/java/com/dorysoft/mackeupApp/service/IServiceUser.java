package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.UserRegistrationDto;

import java.util.List;

public interface IServiceUser {
    List<User> getUsers();
    User getUserById(Long id);
    User saveUser(UserRegistrationDto userDto); // Cambiado para aceptar un DTO
    User updateUser(Long id, UserRegistrationDto userDto); // Cambiado para aceptar un DTO
    void deleteUser(Long id);
}
