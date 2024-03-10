package com.arth.ToDOApi.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.arth.ToDOApi.entities.UserEntity;
import com.arth.ToDOApi.exeptions.TokenNotCreatedExeption;
import com.arth.ToDOApi.exeptions.TokenNotValidExeption;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService 
{
    
    private static final String secret = "ad0e293eb44bd1e81bf5ceeb1f246a55189284a4edd109e924f086ba6e3ef799";
    private static final String issuer = "Arthur's software & web-solutions corp.";

    public String generateToken(UserEntity entity)
    {
        
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(entity.getName())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);

            return token;
        }
        catch(JWTCreationException exception)
        {
            throw new TokenNotCreatedExeption("falha ao criar o token!");
        }

    }

    public String validateToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
            .verify(token)
            .getSubject();
        }
        catch(JWTVerificationException exception)
        {
            throw new TokenNotValidExeption("Token inválido");
        }
    }

    public Instant generateExpirationDate()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
