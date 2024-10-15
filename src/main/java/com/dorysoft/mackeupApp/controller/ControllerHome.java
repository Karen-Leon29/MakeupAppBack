package com.dorysoft.mackeupApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://dnc6ui1xnp6tg.cloudfront.net"})
public class ControllerHome {
    @GetMapping("/")
    public String home() {
        return "Bienvenido a MakeupDoryApp!";
    }
}