package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "currentHolder")
    @JsonIgnore
    private List<Asset> assets;

    @OneToMany(mappedBy = "performedBy")
    @JsonIgnore
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "approvedBy")
    @JsonIgnore
    private List<TransferRecord> transferRecords;

    @OneToMany(mappedBy = "approvedBy")
    @JsonIgnore
    private List<DisposalRecord> disposalRecords;

    public User() {
    }

    public User(String fullName, String email, String department,
                String role, String password) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
    }

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = "USER";
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
}
