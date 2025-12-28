// package com.example.demo.security;

// import com.example.demo.entity.User;
// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class JwtUtil {

//     private static final long EXPIRATION_TIME = 1000 * 60 * 60; 


//     private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//     public String generateToken(Map<String, Object> claims, String subject) {
//         return Jwts.builder()
//                 .claims(claims)
//                 .subject(subject)
//                 .issuedAt(new Date())
//                 .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                 .signWith(key)
//                 .compact();
//     }

//     public String generateTokenForUser(User user) {
//         Map<String, Object> claims = new HashMap<>();
//         claims.put("email", user.getEmail());
//         claims.put("role", user.getRole());
//         claims.put("userId", user.getId());
//         return generateToken(claims, user.getEmail());
//     }

//     public Jws<Claims> parseToken(String token) {
//         return Jwts.parser()
//                 .verifyWith(key)
//                 .build()
//                 .parseSignedClaims(token);
//     }

//     public String extractUsername(String token) {
//         return parseToken(token).getPayload().getSubject();
//     }

//     public String extractRole(String token) {
//         return parseToken(token).getPayload().get("role", String.class);
//     }

//     public Long extractUserId(String token) {
//         Object id = parseToken(token).getPayload().get("userId");
//         return id == null ? null : Long.valueOf(id.toString());
//     }

//     public boolean isTokenExpired(String token) {
//         return parseToken(token).getPayload()
//                 .getExpiration().before(new Date());
//     }

//     public boolean isTokenValid(String token, String username) {
//         return extractUsername(token).equals(username) && !isTokenExpired(token);
//     }
// }
package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    private String secret = "9a4f2c8d3b7a1e6f4d8c2b9a1f4e7d3c6b2a9d1f8e7c3b6a9d2f5e8b1c4a7d3";

    public String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());
        claims.put("email", user.getEmail());
        return generateToken(claims, user.getEmail());
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) { return parseToken(token).getSubject(); }
    public String extractRole(String token) { return (String) parseToken(token).get("role"); }
    public Long extractUserId(String token) { return Long.valueOf(parseToken(token).get("userId").toString()); }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username));
    }
}