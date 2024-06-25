package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.User;

import java.util.List;

public interface IServiceUser {
    List<User> getUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
