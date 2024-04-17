package com.example.TheNews.service.impl;

import com.example.TheNews.entity.UserEntity;
import com.example.TheNews.service.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtServiceImpl implements JwtService {

    private String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";

    private long jwtExpiration = 3600000;

    // Добавляем хранилище для инвалидированных токенов
    private static Set<String> blacklistedTokens = new HashSet<>();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        System.out.println("generateToken: " + jwtExpiration);
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration

    ) {
        String username = userDetails != null ? userDetails.getUsername() : "null";
        System.out.println("Username: " + username);
        System.out.println("Expiration: " + expiration);

        String subject = userDetails != null ? userDetails.getUsername() : null;
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        System.out.println("getSignInKey: " + secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Другие методы класса JwtServiceImpl остаются без изменений

    // Метод для добавления токена в черный список (blacklist)
    public void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }

    // Метод для проверки, инвалидирован ли токен
    public boolean isTokenInvalid(String token) {
        return blacklistedTokens.contains(token);
    }

}
