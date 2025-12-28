package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    // ✅ MANAGED side (one-direction serialization)
    @OneToMany(mappedBy = "currentHolder")
    @JsonManagedReference
    private List<Asset> assets;

    // ❌ Ignore these to avoid deep nesting
    @OneToMany(mappedBy = "performedBy")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "approvedBy")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<TransferRecord> transferRecords;

    @OneToMany(mappedBy = "approvedBy")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<DisposalRecord> disposalRecords;

    public User() {}

    public User(String fullName, String email, String department, String role) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        if (role == null) role = "USER";
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Asset> getAssets() { return assets; }
    public void setAssets(List<Asset> assets) { this.assets = assets; }
}

