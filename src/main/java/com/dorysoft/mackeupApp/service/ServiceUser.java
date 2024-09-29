package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.UserRegistrationDto;
import com.dorysoft.mackeupApp.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser implements IServiceUser{
    @Autowired
    private IRepositoryUser iRepositoryUser;

    @Override
    public List<User> getUsers(){
        return this.iRepositoryUser.findAll();
    }
    @Override
    public User getUserById(Long id) {
        return iRepositoryUser.findById(id).orElse(null);
    }
    @Override
    public User saveUser(@Valid UserRegistrationDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        return iRepositoryUser.save(user);
    }

    @Override
    public User updateUser(Long id, @Valid UserRegistrationDto userDto) {
        return iRepositoryUser.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userDto.getName());
                    existingUser.setLastName(userDto.getLastName());
                    existingUser.setEmail(userDto.getEmail());
                    existingUser.setPhone(userDto.getPhone());
                    existingUser.setPassword(userDto.getPassword());
                    existingUser.setAddress(userDto.getAddress());
                    return iRepositoryUser.save(existingUser);
                })
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        iRepositoryUser.deleteById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return iRepositoryUser.findByEmail(email);
    }
}