package com.example.demo.entity;

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

    @OneToMany(mappedBy = "currentHolder")
    private List<Asset> assets;

    @OneToMany(mappedBy = "performedBy")
    private List<LifecycleEvent> lifecycleEvents;

    @OneToMany(mappedBy = "approvedBy")
    private List<TransferRecord> transferRecords;

    @OneToMany(mappedBy = "approvedBy")
    private List<DisposalRecord> disposalRecords;

    
    public User() {
    }


    public User(String fullName,
                String email,
                String department,
                String role) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    public User(Long id,
                String fullName,
                String email,
                String department,
                String role,
                String password,
                LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
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

    public List<LifecycleEvent> getLifecycleEvents() {
        return lifecycleEvents;
    }

    public List<TransferRecord> getTransferRecords() {
        return transferRecords;
    }

    public List<DisposalRecord> getDisposalRecords() {
        return disposalRecords;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public void setLifecycleEvents(List<LifecycleEvent> lifecycleEvents) {
        this.lifecycleEvents = lifecycleEvents;
    }

    public void setTransferRecords(List<TransferRecord> transferRecords) {
        this.transferRecords = transferRecords;
    }

    public void setDisposalRecords(List<DisposalRecord> disposalRecords) {
        this.disposalRecords = disposalRecords;
    }
}
