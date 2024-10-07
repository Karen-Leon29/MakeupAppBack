package com.dorysoft.mackeupApp.service;

import com.dorysoft.mackeupApp.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userEntity) {
        return generateToken(new HashMap<>(), userEntity);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User userEntity
    ) {
        extraClaims.put("rol", userEntity.getRol());
        extraClaims.put("id", userEntity.getId());

        return buildToken(extraClaims, userEntity, jwtExpiration);
    }

    public String generateRefreshToken(
            User userEntity
    ) {
        return buildToken(new HashMap<>(), userEntity, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User userEntity,
            long expiration
    ) {
        Key key = getSignInKey();

        long current = System.currentTimeMillis();
        long expirationDateInMilliseconds = current + expiration;

        Date expirationDate = new Date(expirationDateInMilliseconds);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userEntity.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        Key key = getSignInKey();

        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getClaims(String token) {
        try {
            return extractAllClaims(token);
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}