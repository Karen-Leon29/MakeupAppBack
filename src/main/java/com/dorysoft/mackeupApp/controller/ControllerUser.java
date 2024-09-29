package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.LoginRequestDto;
import com.dorysoft.mackeupApp.dto.LoginResponseDto;
import com.dorysoft.mackeupApp.dto.UserRegistrationDto;
import com.dorysoft.mackeupApp.service.ServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:4200") // Asegúrate de que la URL tenga dos puntos
@RequestMapping("api-user")
public class ControllerUser {
    public static final Logger logger = LoggerFactory.getLogger(ControllerUser.class);

    @Autowired
    private ServiceUser serviceUser;

    @GetMapping("/listUser")
    public List<User> getUsers() {
        List<User> listUser = this.serviceUser.getUsers();
        logger.info("Obtained user registration");
        listUser.forEach(user -> logger.info(user.toString()));
        return listUser;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = serviceUser.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        if (!registrationDto.getEmail().equals(registrationDto.getConfirmEmail())) {
            return ResponseEntity.badRequest().body("Emails no coinciden.");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Contraseñas no coinciden.");
        }

        User user = serviceUser.saveUser(registrationDto);
        user.setRol("Cliente"); // Asigna el rol por defecto
        return ResponseEntity.ok(serviceUser.updateUser(user.getId(), registrationDto)); // Actualiza el usuario
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        if (!registrationDto.getEmail().equals(registrationDto.getConfirmEmail())) {
            return ResponseEntity.badRequest().body("Emails no coinciden.");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Contraseñas no coinciden.");
        }

        User user = serviceUser.updateUser(id, registrationDto);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (serviceUser.getUserById(id) != null) {
            serviceUser.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        User user = serviceUser.getUserByEmail(loginRequestDto.getEmail()).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado.");
        }

        if (!user.getEmail().equals(loginRequestDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email incorrecto.");
        }

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            return ResponseEntity.badRequest().body("Contraseña incorrecta.");
        }

        return ResponseEntity.ok(user);
    }
}
