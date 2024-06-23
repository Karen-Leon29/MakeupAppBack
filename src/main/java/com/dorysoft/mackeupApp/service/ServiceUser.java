package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.repository.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User saveUser(User user) {
        return iRepositoryUser.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (iRepositoryUser.existsById(user.getId())) {
            return iRepositoryUser.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        iRepositoryUser.deleteById(id);
    }
}
