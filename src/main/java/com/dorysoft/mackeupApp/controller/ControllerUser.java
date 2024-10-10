package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.LoginRequestDto;
import com.dorysoft.mackeupApp.dto.LoginResponseDto;
import com.dorysoft.mackeupApp.dto.TokenRequestDto;
import com.dorysoft.mackeupApp.dto.UserRegistrationDto;
import com.dorysoft.mackeupApp.exceptions.ErrorResponse;
import com.dorysoft.mackeupApp.response.SuccessResponse;
import com.dorysoft.mackeupApp.service.JwtService;
import com.dorysoft.mackeupApp.service.ServiceUser;
import com.dorysoft.mackeupApp.validations.UserRegistrationValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:4200") // Asegúrate de que la URL tenga dos puntos
@RequestMapping("api-user")
@Validated
public class ControllerUser {
    public static final Logger logger = LoggerFactory.getLogger(ControllerUser.class);

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private JwtService serviceJwt;

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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDto registrationDto, BindingResult result) {
        ErrorResponse errorResponse = new ErrorResponse();

        UserRegistrationValidator validator = new UserRegistrationValidator();
        Map<String, List<String>> validationErrors = validator.validate(registrationDto);

        if (!validationErrors.isEmpty()) {
            errorResponse.setCode("ERR_VALIDATION");
            errorResponse.setMessage("Errores de validación de campo.");
            errorResponse.setData(validationErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        if (!registrationDto.getEmail().equals(registrationDto.getConfirmEmail())) {
            errorResponse.setCode("ERR_EMAILS_DO_NOT_MATCH");
            errorResponse.setMessage("Los emails no coinciden.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            errorResponse.setCode("ERR_PASSWORDS_DO_NOT_MATCH");
            errorResponse.setMessage("Las contraseñas no coinciden.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User user = serviceUser.getUserByPhoneOrEmail(registrationDto.getPhone(),registrationDto.getEmail()).orElse(null);
        if (user != null) {
            if(user.getPhone().equals(registrationDto.getPhone())){
                errorResponse.setCode("ERR_PHONE_ALREADY_EXISTS");
                errorResponse.setMessage("El teléfono ya existe.");

            }else if(user.getEmail().equals(registrationDto.getEmail())){
                errorResponse.setCode("ERR_EMAIL_ALREADY_EXISTS");
                errorResponse.setMessage("El email ya existe.");

            }

            return ResponseEntity.badRequest().body(errorResponse);
        }

        user = serviceUser.saveUser(registrationDto);
        user.setRol("Cliente"); // Asigna el rol por defecto

        user = serviceUser.updateUser(user.getId(), registrationDto);
        SuccessResponse<User> successResponse = new SuccessResponse<>("00", "Registered user", user);

        return ResponseEntity.ok(successResponse); // Actualiza el usuario
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

        ErrorResponse errorResponse = new ErrorResponse();
        if (user == null) {
            errorResponse.setCode("ERR_USER_NOT_FOUND");
            errorResponse.setMessage("Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            errorResponse.setCode("ERR_PASSWORD_INCORRECT");
            errorResponse.setMessage("Contraseña incorrecta.");

            return ResponseEntity.badRequest().body(errorResponse);
        }

        String token =  serviceJwt.generateToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);
        loginResponseDto.setUser(user);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequestDto token) {
        String tokenStr = token.getToken();

        System.out.println("Token recibido: " + tokenStr);

        try {
            boolean isvalid = serviceJwt.isTokenValid(tokenStr);

            System.out.println("Token válido: " + isvalid);

            return ResponseEntity.ok(isvalid);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("ERR_TOKEN_NOT_VALID", "Token no válido");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
