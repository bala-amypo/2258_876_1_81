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

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    private String role;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;

    /* ================= RELATIONSHIPS ================= */

    // User → Assets (ONE → MANY)
    @OneToMany(mappedBy = "currentHolder")
    @JsonManagedReference
    private List<Asset> assets;

    @OneToMany(mappedBy = "performedBy")
    @JsonManagedReference
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "approvedBy")
    @JsonManagedReference
    private List<TransferRecord> transferRecords;

    @OneToMany(mappedBy = "approvedBy")
    @JsonManagedReference
    private List<DisposalRecord> disposalRecords;

    /* ================= CONSTRUCTORS ================= */

    public User() {
    }

    /* ================= GETTERS & SETTERS ================= */

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Asset> getAssets() {
        return assets;
    }
}
