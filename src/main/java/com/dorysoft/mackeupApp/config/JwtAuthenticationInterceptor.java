package com.dorysoft.mackeupApp.config;


import com.dorysoft.mackeupApp.exceptions.CustomErrorException;
import com.dorysoft.mackeupApp.exceptions.ErrorResponse;
import com.dorysoft.mackeupApp.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ((request.getServletPath().contains("/auth/") && !request.getServletPath().equals("/auth/logout")
        ) || (request.getServletPath().equals("/error"))) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ErrorResponse.builder().message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build());
        }

        String jwt = authHeader.substring(7);
        try {
            if (!jwtService.isTokenValid(jwt)) {
                throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ErrorResponse.builder().message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build());
            }
        } catch (Exception ex) {
            if (ex instanceof ExpiredJwtException) {
                throw new CustomErrorException(HttpStatus.UNAUTHORIZED, ErrorResponse.builder().message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build());
            }
        }



        return true;
    }
}