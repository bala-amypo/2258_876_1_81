package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 10; // 10 minutes

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    // ✅ REQUIRED BY TESTS
    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }

    public String extractUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public Long extractUserId(String token) {
        return parseToken(token).getPayload().get("userId", Long.class);
    }

    public String extractRole(String token) {
        return parseToken(token).getPayload().get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return parseToken(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }
}
