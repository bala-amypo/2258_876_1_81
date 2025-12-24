package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
@Component
public class JwtUtil {

    private final String SECRET = "secretKey123456";

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String generateTokenForUser(User user) {
        return generateToken(
                Map.of(
                        "userId", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRole()
                ),
                user.getEmail()
        );
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) parseToken(token).getBody().get("role");
    }

    public Long extractUserId(String token) {
        return ((Number) parseToken(token).getBody().get("userId")).longValue();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username);
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }
}
