package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ PUBLIC USER REGISTRATION
    @PostMapping("/register/user")
    public UserResponse registerUser(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        User saved = userService.registerUser(user);
        return mapToResponse(saved);
    }

    // ✅ ADMIN REGISTRATION (ADMIN ONLY)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/admin")
    public UserResponse registerAdmin(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setDepartment(request.getDepartment());
        user.setPassword(request.getPassword());
        user.setRole("ADMIN");

        User saved = userService.registerUser(user);
        return mapToResponse(saved);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.getByEmail(request.getEmail());
        String token = jwtUtil.generateTokenForUser(user);

        return Map.of("token", token);
    }

    // 🔹 Common mapper
    private UserResponse mapToResponse(User saved) {
        return new UserResponse(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getDepartment(),
                saved.getRole(),
                saved.getCreatedAt()
        );
    }
}

