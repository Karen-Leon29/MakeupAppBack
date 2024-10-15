package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.config.Public;
import com.dorysoft.mackeupApp.domain.User;
import com.dorysoft.mackeupApp.dto.*;
import com.dorysoft.mackeupApp.exceptions.ErrorResponse;
import com.dorysoft.mackeupApp.response.SuccessResponse;
import com.dorysoft.mackeupApp.service.JwtService;
import com.dorysoft.mackeupApp.service.ServicePasswordResetToken;
import com.dorysoft.mackeupApp.service.ServiceUser;
import com.dorysoft.mackeupApp.utils.AppConstants;
import com.dorysoft.mackeupApp.utils.Utils;
import com.dorysoft.mackeupApp.validations.LoginUserValidator;
import com.dorysoft.mackeupApp.validations.RecoverPasswordValidator;
import com.dorysoft.mackeupApp.validations.ResetPasswordValidator;
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
@CrossOrigin(origins = {"http://localhost:5173","https://dnc6ui1xnp6tg.cloudfront.net"})
@RequestMapping("api-user")
@Validated
public class ControllerUser {
    public static final Logger logger = LoggerFactory.getLogger(ControllerUser.class);

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private JwtService serviceJwt;

    @Autowired
    private ServicePasswordResetToken servicePasswordResetToken;

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

    @Public
    @PostMapping("/registerUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
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
        user.setRol(AppConstants.ROLE_CLIENTE); // Asigna el rol por defecto

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

    @Public
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        ErrorResponse errorResponse = new ErrorResponse();

        LoginUserValidator validator = new LoginUserValidator();
        Map<String, List<String>> validationErrors = validator.validate(loginRequestDto);

        if (!validationErrors.isEmpty()) {
            errorResponse.setCode("ERR_VALIDATION");
            errorResponse.setMessage("Errores de validación de campo.");
            errorResponse.setData(validationErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User user = serviceUser.getUserByEmail(loginRequestDto.getEmail()).orElse(null);

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

    @Public
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

    @Public
    @PostMapping("/recoverPassword")
    public ResponseEntity<?> recoverPassword(@RequestBody RecoverPasswordRequestDto requestDto) {
        ErrorResponse errorResponse = new ErrorResponse();

        RecoverPasswordValidator validator = new RecoverPasswordValidator();
        Map<String, List<String>> validationErrors = validator.validate(requestDto);

        if (!validationErrors.isEmpty()) {
            errorResponse.setCode("ERR_VALIDATION");
            errorResponse.setMessage("Errores de validación de campo.");
            errorResponse.setData(validationErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User user = serviceUser.getUserByEmail(requestDto.getEmail()).orElse(null);

        if (user == null) {
            errorResponse.setCode("ERR_USER_NOT_FOUND");
            errorResponse.setMessage("Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        String code = Utils.generarCodigo(6); // Genera un código de 6 dígitos
        servicePasswordResetToken.createPasswordResetTokenForUser(user, code);

        SuccessResponse<User> successResponse = new SuccessResponse<>("00", "Code of recovery password sent");
        return ResponseEntity.ok(successResponse);
    }

    @Public
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ResetPasswordRequestDto requestDto) {
        ErrorResponse errorResponse = new ErrorResponse();

        ResetPasswordValidator validator = new ResetPasswordValidator();
        Map<String, List<String>> validationErrors = validator.validate(requestDto);

        if (!validationErrors.isEmpty()) {
            errorResponse.setCode("ERR_VALIDATION");
            errorResponse.setMessage("Errores de validación de campo.");
            errorResponse.setData(validationErrors);
            return ResponseEntity.badRequest().body(errorResponse);
        }

        User user = serviceUser.getUserByEmail(requestDto.getEmail()).orElse(null);

        if (user == null) {
            errorResponse.setCode("ERR_USER_NOT_FOUND");
            errorResponse.setMessage("Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

       String isUpdate = servicePasswordResetToken.changeUserPassword(user, requestDto.getPassword(), requestDto.getCode());

        if(isUpdate.equals("code")){
            errorResponse.setCode("ERR_CODE_NOT_VALID");
            errorResponse.setMessage("Código no válido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else if(isUpdate.equals("expired")){
            errorResponse.setCode("ERR_CODE_EXPIRED");
            errorResponse.setMessage("Código expirado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else if(isUpdate.equals("notfound")){
            errorResponse.setCode("ERR_USER_NOT_FOUND");
            errorResponse.setMessage("Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        SuccessResponse<User> successResponse = new SuccessResponse<>("00", "Password updated");
        return ResponseEntity.ok(successResponse);
    }
}
