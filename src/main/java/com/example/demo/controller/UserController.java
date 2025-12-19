package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
