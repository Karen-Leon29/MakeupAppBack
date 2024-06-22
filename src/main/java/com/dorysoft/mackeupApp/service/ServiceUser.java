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
}
