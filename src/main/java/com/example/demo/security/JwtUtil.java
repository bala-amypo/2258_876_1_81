package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /* =====================================================
       METHODS REQUIRED BY TEST CASES
       ===================================================== */

    // generateToken(claims, subject)
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // generateToken(claims, subject, role)
    public String generateToken(Map<String, Object> claims, String subject, String role) {
        claims.put("role", role);
        return generateToken(claims, subject);
    }

    // parseToken(token)
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody(); // ✅ JJWT 0.11.5
    }

    // extractUserId(token)
    public String extractUserId(String token) {
        return parseToken(token).getSubject();
    }

    /* =====================================================
       BACKWARD-COMPATIBLE METHODS (USED BY YOUR APP)
       ===================================================== */

    // Used by AuthController
    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return generateToken(claims, user.getEmail());
    }

    // Used by JwtAuthenticationFilter
    public String extractUsername(String token) {
        return extractUserId(token);
    }

    public String extractRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }
}
