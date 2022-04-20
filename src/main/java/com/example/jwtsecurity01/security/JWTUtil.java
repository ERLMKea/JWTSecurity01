package com.example.jwtsecurity01.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        String token = JWT.create()
                .withSubject("User details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("MYJWTApp/example/KEA")
                .sign(Algorithm.HMAC256(secret));

        return token;

    }
}
