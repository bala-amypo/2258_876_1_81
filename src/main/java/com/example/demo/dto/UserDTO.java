package com.example.demo.dto;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private String department;
    private String role;
    private LocalDateTime createdAt;

    public UserDTO(Long id, String fullName, String email,
                   String department, String role,
                   LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
