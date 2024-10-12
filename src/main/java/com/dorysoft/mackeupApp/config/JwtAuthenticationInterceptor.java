package com.dorysoft.mackeupApp.config;


import com.dorysoft.mackeupApp.exceptions.CustomErrorException;
import com.dorysoft.mackeupApp.exceptions.ErrorResponse;
import com.dorysoft.mackeupApp.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return  true;
        }

        // Verificar si el handler es una instancia de HandlerMethod (es decir, un método de controlador)
        if (handler instanceof org.springframework.web.method.HandlerMethod) {
            org.springframework.web.method.HandlerMethod handlerMethod = (org.springframework.web.method.HandlerMethod) handler;

            // Verificar si el método o su clase tienen la anotación @Public
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(Public.class) ||
                    handlerMethod.getBeanType().isAnnotationPresent(Public.class)) {
                // Si tiene la anotación @Public, permite el acceso sin validación
                return true;
            }
        }

        ErrorResponse errorResponse = new ErrorResponse();

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            errorResponse.setCode("ERR_NO_AUTH_HEADER");
            errorResponse.setMessage("No Authorization header");

            return handleUnauthorized(response, errorResponse);
        }

        String jwt = authHeader.substring(7);

        try {
            if (!jwtService.isTokenValid(jwt)) {
                errorResponse.setCode("ERR_INVALID_TOKEN");
                errorResponse.setMessage("Token is not valid");

                return handleUnauthorized(response, errorResponse);
            }
        } catch (ExpiredJwtException ex) {
            errorResponse.setCode("ERR_TOKEN_EXPIRED");
            errorResponse.setMessage("Token has expired");

            return handleUnauthorized(response, errorResponse);
        } catch (Exception ex) {
            errorResponse.setCode("ERR_INVALID_TOKEN");
            errorResponse.setMessage("Token is not valid");

            return handleUnauthorized(response, errorResponse);
        }

        Claims claims = jwtService.getClaims(jwt);
        request.setAttribute("claims", claims);

        return true;
    }

    private boolean handleUnauthorized(HttpServletResponse response, ErrorResponse errorResponse) throws Exception {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        return false;
    }
}