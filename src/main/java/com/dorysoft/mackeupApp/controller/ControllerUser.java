package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Long id) {
        return serviceUser.getUserById(id);
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return serviceUser.saveUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return serviceUser.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        serviceUser.deleteUser(id);
    }
}
