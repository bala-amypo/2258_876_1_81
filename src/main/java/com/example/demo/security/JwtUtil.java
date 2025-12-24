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
       TOKEN GENERATION
       ===================================================== */

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String generateToken(Map<String, Object> claims, String subject, String role) {
        claims.put("role", role);
        return generateToken(claims, subject);
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /* =====================================================
       TOKEN PARSING
       ===================================================== */

    // IMPORTANT: must return Jws<Claims> for getPayload()
    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    public Long extractUserId(String token) {
        Object id = parseToken(token).getBody().get("userId");
        return id == null ? null : Long.valueOf(id.toString());
    }

    /* =====================================================
       VALIDATION
       ===================================================== */

    public boolean isTokenExpired(String token) {
        return parseToken(token).getBody()
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username) && !isTokenExpired(token);
    }
}
