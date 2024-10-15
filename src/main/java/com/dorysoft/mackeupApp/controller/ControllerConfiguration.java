package com.dorysoft.mackeupApp.controller;

import com.dorysoft.mackeupApp.config.Public;
import com.dorysoft.mackeupApp.domain.Configuration;
import com.dorysoft.mackeupApp.exceptions.ErrorResponse;
import com.dorysoft.mackeupApp.response.SuccessResponse;
import com.dorysoft.mackeupApp.service.ServiceConfiguration;
import com.dorysoft.mackeupApp.utils.AppConstants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://dnc6ui1xnp6tg.cloudfront.net"})
@RequestMapping("/api/configuration")
@Validated
public class ControllerConfiguration {
    @Autowired
    private ServiceConfiguration configurationService;

    // Endpoint para obtener la configuración
    @Public
    @GetMapping
    public ResponseEntity<?> getConfiguration() {
        Configuration config = configurationService.getConfiguration();

        SuccessResponse<Configuration> successResponse = new SuccessResponse<>("00", "Configuration found", config);

        return ResponseEntity.ok(successResponse);
    }

    // Endpoint para actualizar la configuración
    @PutMapping
    public ResponseEntity<?> updateConfiguration(@RequestBody Configuration newConfig, HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");

        String role = (String) claims.get("rol");
        if(!role.equals(AppConstants.ROLE_ADMINISTRADOR)) {
            ErrorResponse errorResponse = new ErrorResponse("ERR_NOT_AUTHORIZED", "You don't have permission to update the configuration");
            return ResponseEntity.status(403).body(errorResponse);
        }

        Configuration updatedConfig = configurationService.updateConfiguration(newConfig);

        SuccessResponse<Configuration> successResponse = new SuccessResponse<>("00", "Configuration found", updatedConfig);

        return ResponseEntity.ok(successResponse);
    }
}
