package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(value = "http//localhost:4200")
@RequestMapping("api-user")
public class ControllerUser {
    public static final Logger logger = LoggerFactory.getLogger(ControllerUser.class);

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/listUser")
    public List<User> getUsers(){
        List<User> listUser = this.serviceUser.getUsers();
        logger.info("Obtained user registration");
        listUser.forEach((user -> logger.info(user.toString())));
        return listUser;
    }
}
