package com.dorysoft.mackeupApp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class MackeupAppApplication {

	public static void main(String[] args) {
		/*
		String secretKey = "X7SLs79k1VD2gN4VT6ErzV9SDuzdzYXgeMWLFeRHkOI=";
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		Key key = Keys.hmacShaKeyFor(keyBytes);

		// Genera el token
		String token = Jwts.builder()
				.setSubject("usuarioPrueba")
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

		System.out.println("Token generado: " + token);

		// Valida el token
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

		System.out.println("Claims: " + claims.getSubject());
		*/

		SpringApplication.run(MackeupAppApplication.class, args);
	}

}
